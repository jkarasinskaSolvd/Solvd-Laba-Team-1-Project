package solvd.laba.model;

public class Distance {
    private Long idWarehouse;
    private Long idDeliveryAddress;
    private double distanceInKm;
    public Distance(Long idWarehouse, Long idDeliveryAddress, double distanceInKm) {
        this.idWarehouse = idWarehouse;
        this.idDeliveryAddress = idDeliveryAddress;
        this.distanceInKm = distanceInKm;
    }

    // Геттеры и сеттеры
    public Long getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(Long idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public Long getIdDeliveryAddress() {
        return idDeliveryAddress;
    }

    public void setIdDeliveryAddress(Long idDeliveryAddress) {
        this.idDeliveryAddress = idDeliveryAddress;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }
}
