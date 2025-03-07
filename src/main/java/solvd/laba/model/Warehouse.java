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

    public Warehouse(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.availableProducts = builder.availableProducts;
        this.allowedTransportTypes = builder.allowedTransportTypes;
    }

    public static class Builder {
        private Long id;
        private Address address;
        private List<Product> availableProducts;
        private List<TransportType> allowedTransportTypes;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder availableProducts(List<Product> availableProducts) {
            this.availableProducts = availableProducts;
            return this;
        }

        public Builder allowedTransportTypes(List<TransportType> allowedTransportTypes) {
            this.allowedTransportTypes = allowedTransportTypes;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }

    @Override
    public String toString() {
        String string =  "Warehouse{" +
                "id=" + id +
                ", address=" + address + " available Products: ";

        for (Product product : availableProducts) {
            string += ", " + product;
        }

        string += " allowed transportTypes: ";
        for (TransportType transportType : allowedTransportTypes) {
            string += ", " + transportType;
        }

        string += "}";

        return string;
    }
}
