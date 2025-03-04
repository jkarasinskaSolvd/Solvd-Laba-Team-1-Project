package solvd.laba.service.impl;

import solvd.laba.model.Address;
import solvd.laba.model.Distance;
import solvd.laba.model.Warehouse;
import solvd.laba.service.IDistanceService;

import java.math.BigDecimal;
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
        public Optional<BigDecimal> getDistance(Warehouse warehouse, Address deliveryAddress) {
            return distances.stream()
                    .filter(distance -> distance.getWarehouse().equals(warehouse) && distance.getDeliveryAddress().equals(deliveryAddress))
                    .map(Distance::getDistance)
                    .findFirst();
        }
    @Override
    public List<Distance> getAllDistances() {
        return distances;
    }
}
