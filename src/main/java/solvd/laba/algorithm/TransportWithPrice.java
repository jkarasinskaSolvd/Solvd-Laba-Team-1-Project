package solvd.laba.algorithm;

import solvd.laba.model.Company;
import solvd.laba.model.Transport;


public class TransportWithPrice {
    Transport transport;
    Double fullPrice;
    Company company = null;

    public TransportWithPrice(Transport transport, Double fullPrice) {
        this.transport = transport;
        this.fullPrice = fullPrice;
    }

    public TransportWithPrice(Transport transport, Double fullPrice, Company company) {
        this.transport = transport;
        this.fullPrice = fullPrice;
        this.company = company;
    }

    public Transport getTransport() {
        return transport;
    }

    public Double getFullPrice() {
        return fullPrice;
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
