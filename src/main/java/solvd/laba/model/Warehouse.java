package solvd.laba.model;

import java.util.List;

public class Warehouse {
    private Long id;
    private Address address;
    private List<Product> availableProducts;
    private List<TransportType> allowedTransportTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(List<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public List<TransportType> getAllowedTransportTypes() {
        return allowedTransportTypes;
    }

    public void setAllowedTransportTypes(List<TransportType> allowedTransportTypes) {
        this.allowedTransportTypes = allowedTransportTypes;
    }
}
