package solvd.laba.service;

import solvd.laba.model.Address;
import solvd.laba.model.Distance;
import solvd.laba.model.Warehouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface IDistanceService {
    Optional<BigDecimal> getDistance(Warehouse warehouse, Address deliveryAddress);

    List<Distance> getAllDistances();
}
