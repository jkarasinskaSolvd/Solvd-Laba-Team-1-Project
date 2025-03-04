package solvd.laba.idao;


import solvd.laba.model.Distance;



public interface IDaoDistance extends IDao<Distance> {
    Distance readByIds(Long warehouseId, Long deliveryAddressId);
    Boolean removeByIds(Long warehouseId, Long deliveryAddressId);
    Boolean removeByDelivery(Long deliveryAddressId);
    Boolean removeByWarehouse(Long warehouseId);
}
