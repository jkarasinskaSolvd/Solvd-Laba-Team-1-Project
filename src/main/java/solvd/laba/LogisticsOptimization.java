package solvd.laba;

import solvd.laba.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LogisticsOptimization {

    // Метод для оптимизации логистики с учетом расстояния
    public List<DeliveryOption> optimizeLogistics(Order order, List<Warehouse> warehouses, List<Company> companies, List<Distance> distances) {
        List<DeliveryOption> options = new ArrayList<>();

        // Для каждого склада
        for (Warehouse warehouse : warehouses) {
            // Для каждого товара в заказе
            for (OrderItem orderItem : order.getOrderItems()) {
                // Для каждого транспортного средства в компании
                for (Company company : companies) {
                    for (Transport transport : company.getAvailableVehicles()) {
                        // Проверяем, может ли транспортное средство перевезти товар
                        BigDecimal totalVolume = orderItem.getProduct().getVolume().multiply(new BigDecimal(orderItem.getQuantity()));
                        if (transport.getMaxCapacity().compareTo(totalVolume) >= 0) {

                            // Ищем расстояние для данного склада и адреса доставки
                            Optional<Distance> distanceOpt = distances.stream()
                                    .filter(d -> d.getIdWarehouse().equals(warehouse.getId()) && d.getIdDeliveryAddress().equals(order.getAddress().getId()))
                                    .findFirst();

                            if (distanceOpt.isPresent()) {
                                Distance distance = distanceOpt.get();
                                double distanceInKm = distance.getDistanceInKm();

                                // Рассчитываем стоимость доставки с учетом расстояния
                                BigDecimal cost = transport.getCostPerKm().multiply(new BigDecimal(distanceInKm));
                                options.add(new DeliveryOption(warehouse, transport, cost, distanceInKm));
                            }
                        }
                    }
                }
            }
        }

        // Сортируем по стоимости доставки (возрастающая стоимость)
        options.sort(Comparator.comparing(DeliveryOption::getCost));

        return options;
    }
}
