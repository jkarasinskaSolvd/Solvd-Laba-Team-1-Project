package solvd.laba.algorithm;

import solvd.laba.model.*;
import solvd.laba.service.IWarehouseService;

import java.math.BigDecimal;
import java.util.*;

public class Algorithm {
    private final IWarehouseService warehouseService;


    public Algorithm(IWarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    private Map.Entry<Warehouse, Double> findWarehouse(Order order) {

        List<Warehouse> warehouses =  warehouseService.readAll();

        Map<Warehouse, Double> warehouseWithDistanceMap = new HashMap<>();


        List<Long> productsIds = order.getOrderItems().stream()
                .map(orderItem -> orderItem.getProduct().getId())
                .toList();

        //chceking if warehouse have all products
        warehouses = warehouses.stream()
                .filter(warehouse ->
                        new HashSet<>(warehouse.getAvailableProducts().stream()
                                .map(Product::getId)
                                .toList()).containsAll(productsIds)
                ).toList();


        for (Warehouse warehouse : warehouses) {
            warehouseWithDistanceMap.put(warehouse,calculateDistance(warehouse.getAddress(), order.getAddress()));
        }


        Map.Entry<Warehouse, Double> min = null;
        for (Map.Entry<Warehouse, Double> entry : warehouseWithDistanceMap.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }

        return min;
    }

    private Double calculateDistance(Address address1, Address address2) {
        return Math.sqrt(Math.pow(address2.getX() - address1.getX(), 2) + Math.pow(address2.getY() - address1.getY(), 2));
    }

    private TransportWithPrice findCheapestTransportOptionFromLogisticCompany(Company company,
                                                      Map.Entry<Warehouse, Double> warehouseWithDistance, Order order ){
        //get list of transport options available for warehouse
        List<Transport> transports = new ArrayList<>(company.getAvailableVehicles().stream()
                .filter(transport -> warehouseWithDistance.getKey().getAllowedTransportTypes().contains(transport.getType()))
                .toList());

        if (transports.isEmpty()) {
            return null;
        }

        BigDecimal orderVolume = BigDecimal.ZERO;
        for (int i = 0; i < order.getOrderItems().size(); i++){
            orderVolume = orderVolume.add(
                    order.getOrderItems().get(i).getProduct().getVolume()
                            .multiply(BigDecimal.valueOf(order.getOrderItems().get(i).getQuantity())));
        }


        //we leave only vehicles that can fit entire order
        BigDecimal finalOrderVolume = orderVolume;
        transports.removeIf(transport -> finalOrderVolume.compareTo(transport.getMaxCapacity()) >= 0);

        List<TransportWithPrice> transportWithPrices = new ArrayList<>();


        for (Transport transport : transports) {
            Double fullPrice = warehouseWithDistance.getValue();
            fullPrice = fullPrice * transport.getCostPerKm().toBigInteger().doubleValue();
            transportWithPrices.add(new TransportWithPrice
                    (transport, fullPrice, company
            ));
        }

        transportWithPrices = transportWithPrices.stream()
                .sorted(Comparator.comparing(TransportWithPrice::getFullPrice))
                .toList();

        return  transportWithPrices.get(0);
    }

    public Map<Order,TransportWithPrice> compare(List<Company> companies, List<Order> orders){
        Map<Order,TransportWithPrice> transportWithPricesMap = new HashMap<>();
        Map.Entry<Warehouse, Double> warehouseWithDistance;

        List<TransportWithPrice> transportWithPricesList = new ArrayList<>();
        for (Order order : orders) {
            warehouseWithDistance = findWarehouse(order);
            for (Company company : companies) {
                transportWithPricesList.add(
                        findCheapestTransportOptionFromLogisticCompany(company, warehouseWithDistance, order));
            }
            transportWithPricesList = transportWithPricesList.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing(TransportWithPrice::getFullPrice))
                    .toList();
            transportWithPricesMap.put(order,transportWithPricesList.get(0));
            transportWithPricesList = new ArrayList<>();
        }

        return  transportWithPricesMap;
    }

    public String printResults(Map<Order,TransportWithPrice> transportWithPricesMap){
        StringBuilder stringBuilder = new StringBuilder("\n\nRESULTS \n\n");
        for (Map.Entry<Order,TransportWithPrice> entry : transportWithPricesMap.entrySet()) {
            stringBuilder.append(entry.getKey().toString()).append('\n');
            stringBuilder.append(entry.getValue().toString()).append('\n');
            stringBuilder.append("----------------------------"+'\n');
        }
        return stringBuilder.toString();
    }
}
