package solvd.laba;

import solvd.laba.model.Transport;
import solvd.laba.model.Warehouse;

import java.math.BigDecimal;

class DeliveryOption {

    private Warehouse warehouse;
    private Transport transport;
    private BigDecimal cost;
    private double distance;

    // Конструктор
    public DeliveryOption(Warehouse warehouse, Transport transport, BigDecimal cost, double distance) {
        this.warehouse = warehouse;
        this.transport = transport;
        this.cost = cost;
        this.distance = distance;
    }

    // Геттеры
    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Transport getTransport() {
        return transport;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public double getDistance() {
        return distance;
    }
}
