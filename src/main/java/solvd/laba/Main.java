package solvd.laba;

import solvd.laba.algorithm.Algorithm;
import solvd.laba.algorithm.TransportWithPrice;
import solvd.laba.idao.*;
import solvd.laba.model.*;
import solvd.laba.service.*;
import solvd.laba.service.impl.*;
import solvd.laba.sql.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //adding products
        IDaoProduct productDao = new SqlDaoProduct();
        IProductService productService = new ProductServiceImpl(productDao);

        Product.Builder builder = new Product.Builder();
//        builder.id(1L);
//        builder.name("milk");
//        builder.price(BigDecimal.valueOf(15.00));
//        builder.volume(BigDecimal.valueOf(2));
//        Product milk = new Product(builder);
//        productService.create(milk);


//        builder.id(2L);
//        builder.name("chocolate");
//        builder.price(BigDecimal.valueOf(17.00));
//        builder.volume(BigDecimal.valueOf(3));
//        Product chocolate = new Product(builder);
//        productService.create(chocolate);
//
//        builder.id(3L);
//        builder.name("water");
//        builder.price(BigDecimal.valueOf(11.00));
//        builder.volume(BigDecimal.valueOf(1));
//        Product milk = new Product(builder);
//
//        productService.create(milk);

//        productService.remove(1L);
//        productService.remove(2L);
//        productService.remove(3L);

        // adding adressess
        IDaoAddress addressDao = new SqlDaoAddress();
        IAddressService addressService = new AddressServiceImpl(addressDao);

        Address.Builder addressBuilder = new Address.Builder();

//        addressBuilder.country("USA");
//        addressBuilder.city("London");
//        addressBuilder.apartment("12");
//        addressBuilder.building("1");
//        addressBuilder.postalCode("1");
//        addressBuilder.street("1");
//        addressBuilder.x(0.0);
//        addressBuilder.y(0.0);
//        Address address1 = addressService.create(addressBuilder.build());
//
//        addressBuilder.country("USA");
//        addressBuilder.city("London");
//        addressBuilder.apartment("12");
//        addressBuilder.building("1");
//        addressBuilder.postalCode("1");
//        addressBuilder.street("1");
//        addressBuilder.x(2.0);
//        addressBuilder.y(0.0);
//        Address address2 = addressService.create(addressBuilder.build());
//
//        addressBuilder.country("USA");
//        addressBuilder.city("London");
//        addressBuilder.apartment("12");
//        addressBuilder.building("1");
//        addressBuilder.postalCode("1");
//        addressBuilder.street("1");
//        addressBuilder.x(3.0);
//        addressBuilder.y(0.0);
//        Address address3 = addressService.create(addressBuilder.build());

//        addressService.remove(address1.getId());
//        addressService.remove(address2.getId());
//        addressService.remove(address3.getId());

        IDaoWarehouse warehouseDao = new SqlDaoWarehouse();
        IWarehouseService warehouseService = new WarehouseServiceImpl(warehouseDao);

        IDaoCompany companyDao = new SqlDaoCompany();
        ICompanyService companyService = new CompanyServiceImpl(companyDao);

        IDaoOrder orderDao = new SqlDaoOrder();
        IOrderService orderService = new OrderServiceImpl(orderDao);


        Algorithm algorithm = new Algorithm(warehouseService);

        List<Company> companies = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Warehouse> warehouses = new ArrayList<>();

        companies = companyService.readAll();
        orders = orderService.readAll();
        warehouses = warehouseService.readAll();

        for (Company company : companies) {
            System.out.println(company);
        }

        for (Order order : orders) {
            System.out.println(order);
        }

        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }

        Map< Order, TransportWithPrice> orderTransportWithPriceMap=  algorithm.compare(companies, orders);
        algorithm.printResults(orderTransportWithPriceMap);
    }
}
