package solvd.laba.service.impl;


import solvd.laba.idao.IDaoDistance;


import solvd.laba.model.Distance;
import solvd.laba.service.IDistanceService;

import java.util.List;


public class DistanceServiceImpl  implements IDistanceService {
    private final IDaoDistance daoDistance;

    public DistanceServiceImpl(IDaoDistance daoDistance) {
        this.daoDistance = daoDistance;
    }

    @Override
    public Distance create(Distance distance) {
        if (distance == null || distance.getWarehouse() == null || distance.getDeliveryAddress() == null) {
            throw new IllegalArgumentException("Invalid Distance object or its related entities");
        }
        return daoDistance.create(distance);
    }

    @Override
    public Distance read(Long id) {
        return daoDistance.read(id);
    }

    @Override
    public List<Distance> readAll() {
        return daoDistance.readAll();
    }

    @Override
    public Distance update(Distance distance) {
        if (distance == null || distance.getWarehouse() == null || distance.getDeliveryAddress() == null) {
            throw new IllegalArgumentException("Invalid Distance object or its related entities");
        }
        return daoDistance.update(distance);
    }


    @Override
    public Boolean removeByIds(Long warehouseId, Long deliveryAddressId) {
        if (warehouseId == null || deliveryAddressId == null) {
            throw new IllegalArgumentException("Invalid IDs");
        }
        return daoDistance.removeByIds(warehouseId, deliveryAddressId);
    }
    @Override
    public Boolean removeByWarehouse(Long warehouseId) {
        if (warehouseId == null) {
            throw new IllegalArgumentException("Invalid warehouse ID");
        }
        return daoDistance.removeByWarehouse(warehouseId);
    }
    @Override
    public Boolean removeByDelivery(Long deliveryAddressId) {
        if (deliveryAddressId == null) {
            throw new IllegalArgumentException("Invalid delivery address ID");
        }
        return daoDistance.removeByDelivery(deliveryAddressId);
    }

}
