package solvd.laba.model;

public enum TransportType {
    TRUCK("Truck"),
    TRAIN("Train"),
    AIRPLANE("Airplane");

    private final String name;

    TransportType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
