package solvd.laba.model;

public class Address {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String building;
    private String apartment;
    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Address(Builder builder) {
        this.id = builder.id;
        this.country = builder.country;
        this.city = builder.city;
        this.street = builder.street;
        this.building = builder.building;
        this.apartment = builder.apartment;
        this.postalCode = builder.postalCode;
    }

    public static class Builder {
        private Long id;
        private String country;
        private String city;
        private String street;
        private String building;
        private String apartment;
        private String postalCode;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder building(String building) {
            this.building = building;
            return this;
        }

        public Builder apartment(String apartment) {
            this.apartment = apartment;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
