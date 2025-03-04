package solvd.laba.algorithm;

import solvd.laba.model.*;

import java.math.BigDecimal;
import java.util.*;

public class Algorithm {
    public Algorithm() {
        System.out.println("It's an utility class");
    }

    private static Warehouse findAllProductsInWarehouse(Order order) {
        //ordering list of warehouses which contain order address by distance

        // TODO: select distances from DB
        List<Distance> distances = new ArrayList<>(); //will be selected from DB later

        List<Warehouse> sortedWarehouses = distances.stream()
                .filter(distance -> Objects.equals(distance.getDeliveryAddress().getId(), order.getAddress().getId()))
                .sorted(Comparator.comparing(Distance::getDistance))
                .map(Distance::getWarehouse)
                .toList();

        //finding the closest Warehouse that has all ordered products
        List<Long> productsIds = order.getOrderItems().stream()
                .map(orderItem -> orderItem.getProduct().getId())
                .toList();

        //for now always return null because distances is empty
        return sortedWarehouses.stream()
                .filter(warehouse ->
                     new HashSet<>(warehouse.getAvailableProducts().stream()
                             .map(Product::getId)
                             .toList()).containsAll(productsIds)
                ).findFirst().orElse(null);

    }

    private static TransportWithPrice findCheapestTransportOptionFromLogisticCompany(Company company, Warehouse warehouse,
                                                                                     Order order){
        //get list of transport options available for warehouse
        List<Transport> transports = new ArrayList<>(company.getAvailableVehicles().stream()
                .filter(transport -> warehouse.getAllowedTransportTypes().contains(transport.getType()))
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

        //implemenatation when the whole order fits in one vehicle

        //we leave only vehicles that can fit entire order
        for (Transport transport : transports) {
            if(orderVolume.compareTo(transport.getMaxCapacity()) == 1){
                transports.remove(transport);
            }
        }

        List<TransportWithPrice> transportWithPrices = new ArrayList<>();

        // TODO: Select distance based on id's from DB
        Distance distance = new Distance(new Distance.Builder());

        for (Transport transport : transports) {
            transportWithPrices.add(new TransportWithPrice
                    (transport,distance.getDistance().multiply(transport.getCostPerKm())));
        }

        transportWithPrices = transportWithPrices.stream()
                .sorted(Comparator.comparing(TransportWithPrice::getFullPrice))
                .toList();

        return  transportWithPrices.getFirst();
    }

    public static Map<Order,TransportWithPrice> compare(List<Company> companies, List<Order> orders){
        Map<Order,TransportWithPrice> transportWithPricesMap = new HashMap<>();
        Warehouse warehouse = null;

        List<TransportWithPrice> transportWithPricesList = new ArrayList<>();
        for (Order order : orders) {
            warehouse = findAllProductsInWarehouse(order);
            for (Company company : companies) {
                transportWithPricesList.add(findCheapestTransportOptionFromLogisticCompany(company, warehouse, order));
            }
            transportWithPricesList = transportWithPricesList.stream()
                    .sorted(Comparator.comparing(TransportWithPrice::getFullPrice))
                    .toList();
            transportWithPricesMap.put(order,transportWithPricesList.getFirst());
            transportWithPricesList = new ArrayList<>();
        }

        return  transportWithPricesMap;
    }

    public static void printResults(Map<Order,TransportWithPrice> transportWithPricesMap){
        for (Map.Entry<Order,TransportWithPrice> entry : transportWithPricesMap.entrySet()) {
            System.out.println(entry.getKey().toString());
            System.out.println(entry.getValue().toString());
            System.out.println("----------------------------");
        }

    }
}
