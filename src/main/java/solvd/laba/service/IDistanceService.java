package solvd.laba.service;

import solvd.laba.model.Distance;

import java.util.Optional;

public interface IDistanceService {
    Optional<Distance> getDistance(Long warehouseId, Long deliveryAddressId);

}
