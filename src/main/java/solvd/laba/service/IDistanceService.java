package solvd.laba.service;


import solvd.laba.model.Distance;

import java.util.List;

public interface IDistanceService {
    Boolean removeByIds(Long warehouseId, Long deliveryAddressId);
    Boolean removeByDelivery(Long deliveryAddressId);
    Boolean removeByWarehouse(Long warehouseId);
    Distance create(Distance entity);
    Distance read(Long id);
    List<Distance> readAll();
    Distance update(Distance entity);

}

