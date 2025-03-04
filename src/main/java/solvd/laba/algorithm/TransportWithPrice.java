package solvd.laba.algorithm;

import solvd.laba.model.Company;
import solvd.laba.model.Transport;

import java.math.BigDecimal;
import java.util.List;

public class TransportWithPrice {
    Transport transport;
    BigDecimal fullPrice;
    Company company = null;

    public TransportWithPrice(Transport transport, BigDecimal fullPrice) {
        this.transport = transport;
        this.fullPrice = fullPrice;
    }

    public TransportWithPrice(Transport transport, BigDecimal fullPrice, Company company) {
        this.transport = transport;
        this.fullPrice = fullPrice;
        this.company = company;
    }

    public Transport getTransport() {
        return transport;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "TransportWithPrice{" +
                "transport=" + transport +
                ", fullPrice=" + fullPrice +
                ", logistic company name=" + company.getName() +
                '}';
    }
}
