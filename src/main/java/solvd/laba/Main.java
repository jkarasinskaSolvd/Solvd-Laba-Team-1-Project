package solvd.laba;

import solvd.laba.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Создаем продукты
        Product product1 = new Product.Builder().id(1L).name("Product 1").price(new BigDecimal("10.0")).volume(new BigDecimal("2.0")).build();
        Product product2 = new Product.Builder().id(2L).name("Product 2").price(new BigDecimal("15.0")).volume(new BigDecimal("1.5")).build();

        // 2. Создаем склад
        Warehouse warehouse = new Warehouse.Builder().id(1L).address(new Address.Builder().id(1L).country("USA").city("New York").street("5th Avenue").build())
                .availableProducts(Arrays.asList(product1, product2))
                .allowedTransportTypes(Arrays.asList(TransportType.TRACK, TransportType.TRAIN))
                .build();

        // 3. Создаем компанию и транспортные средства
        Transport transport = new Transport.Builder().id(1L).type(TransportType.TRACK).maxCapacity(new BigDecimal("10.0")).costPerKm(new BigDecimal("2.0")).build();
        Company company = new Company.Builder().id(1L).name("Company A").availableVehicles(Arrays.asList(transport)).build();

        // 4. Создаем заказ
        OrderItem orderItem = new OrderItem.Builder().product(product1).quantity(3).build();
        Order order = new Order.Builder().id(1L).orderItems(Arrays.asList(orderItem)).address(new Address.Builder().id(2L).country("USA").city("Chicago").street("Main Street").build()).build();

        // 5. Создаем расстояния
        List<Distance> distances = Arrays.asList(new Distance(1L, 2L, 100.0));  // 100 км от склада до адреса доставки

        // 6. Оптимизируем логистику
        LogisticsOptimization optimization = new LogisticsOptimization();
        List<DeliveryOption> deliveryOptions = optimization.optimizeLogistics(order, Arrays.asList(warehouse), Arrays.asList(company), distances);

        // 7. Выводим результаты
        for (DeliveryOption option : deliveryOptions) {
            System.out.println("Warehouse: " + option.getWarehouse().getId() + ", Transport: " + option.getTransport().getType() +
                    ", Distance: " + option.getDistance() + " km, Cost: " + option.getCost());
        }
    }
}
