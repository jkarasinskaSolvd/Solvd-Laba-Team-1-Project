package solvd.laba.model;

import java.util.List;

public class Order {
    private Long id;
    private List<OrderItem> orderItems;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Order(Builder builder) {
        this.id = builder.id;
        this.orderItems = builder.orderItems;
        this.address = builder.address;
    }

    public static class Builder {
        private Long id;
        private List<OrderItem> orderItems;
        private Address address;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder orderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", address=" + address +
                '}';
    }
}
