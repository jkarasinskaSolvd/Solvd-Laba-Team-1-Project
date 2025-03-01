package solvd.laba.service.impl;

import solvd.laba.model.Distance;
import solvd.laba.service.IDistanceService;

import java.util.List;
import java.util.Optional;

public class DistanceServiceImpl implements IDistanceService {


        private List<Distance> distances;

        // Constructor that accepts a list of distances
    public DistanceServiceImpl(List<Distance> distances) {
            this.distances = distances;
        }

        // Implementation of a method to obtain distance for a specific warehouse and delivery address
        @Override
        public Optional<Distance> getDistance(Long warehouseId, Long deliveryAddressId) {
            return distances.stream()
                    .filter(distance -> distance.getIdWarehouse()
                            .equals(warehouseId) && distance.getIdDeliveryAddress().equals(deliveryAddressId))
                    .findFirst();
        }

}
