package solvd.laba.model;

import java.util.List;

public class Company {
    private Long id;
    private String name;
    private List<Transport> availableVehicles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transport> getAvailableVehicles() {
        return availableVehicles;
    }

    public void setAvailableVehicles(List<Transport> availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

    public Company(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.availableVehicles = builder.availableVehicles;
    }

    public static class Builder {
        private Long id;
        private String name;
        private List<Transport> availableVehicles;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder availableVehicles(List<Transport> availableVehicles) {
            this.availableVehicles = availableVehicles;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }
}
