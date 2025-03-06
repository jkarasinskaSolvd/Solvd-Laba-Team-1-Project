package solvd.laba;

import solvd.laba.idao.IDaoAddress;
import solvd.laba.idao.IDaoCompany;
import solvd.laba.idao.IDaoProduct;
import solvd.laba.model.Address;
import solvd.laba.model.Company;
import solvd.laba.model.Product;
import solvd.laba.service.IAddressService;
import solvd.laba.service.ICompanyService;
import solvd.laba.service.IProductService;
import solvd.laba.service.impl.AddressServiceImpl;
import solvd.laba.service.impl.CompanyServiceImpl;
import solvd.laba.service.impl.ProductServiceImpl;
import solvd.laba.sql.SqlDaoAddress;
import solvd.laba.sql.SqlDaoCompany;
import solvd.laba.sql.SqlDaoProduct;

import java.math.BigDecimal;

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

        // adding Logistic Companies

        IDaoCompany companyDao = new SqlDaoCompany();
        ICompanyService companyService = new CompanyServiceImpl(companyDao);

        Company.Builder companyBuilder = new Company.Builder();

    }
}
