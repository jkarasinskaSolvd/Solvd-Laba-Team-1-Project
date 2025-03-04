package solvd.laba.model;

import java.math.BigDecimal;

public class Distance {
    private Warehouse warehouse;
    private Address deliveryAddress;
    private BigDecimal distance;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public Distance(Builder builder) {
        this.warehouse = builder.warehouse;
        this.deliveryAddress = builder.deliveryAddress;
        this.distance = builder.distance;
    }

    public static class Builder {
        private Warehouse warehouse;
        private Address deliveryAddress;
        private BigDecimal distance;

        public Builder warehouse(Warehouse warehouse) {
            this.warehouse = warehouse;
            return this;
        }

        public Builder deliveryAddress(Address deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Builder distance(BigDecimal distance) {
            this.distance = distance;
            return this;
        }

        public Distance build() {
            return new Distance(this);
        }
    }
}
