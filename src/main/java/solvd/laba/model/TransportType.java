package solvd.laba.model;

public enum TransportType {
    TRACK("Truck"),
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
