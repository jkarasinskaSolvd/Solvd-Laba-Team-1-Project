package solvd.laba.model;

import java.math.BigDecimal;

public class Transport {
    private Long id;
    private TransportType type;
    private BigDecimal maxCapacity;
    private BigDecimal costPerKm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public BigDecimal getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(BigDecimal maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public BigDecimal getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(BigDecimal costPerKm) {
        this.costPerKm = costPerKm;
    }

    public Transport(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.maxCapacity = builder.maxCapacity;
        this.costPerKm = builder.costPerKm;
    }

    public static class Builder {
        private Long id;
        private TransportType type;
        private BigDecimal maxCapacity;
        private BigDecimal costPerKm;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder type(TransportType type) {
            this.type = type;
            return this;
        }

        public Builder maxCapacity(BigDecimal maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public Builder costPerKm(BigDecimal costPerKm) {
            this.costPerKm = costPerKm;
            return this;
        }

        public Transport build() {
            return new Transport(this);
        }
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", type=" + type +
                ", maxCapacity=" + maxCapacity +
                ", costPerKm=" + costPerKm +
                '}';
    }
}
