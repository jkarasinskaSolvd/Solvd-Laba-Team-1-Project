package solvd.laba.service.impl;

import solvd.laba.idao.IDaoWarehouse;
import solvd.laba.model.Warehouse;
import solvd.laba.service.IWarehouseService;

import java.util.List;

public class WarehouseServiceImpl implements IWarehouseService {
    private final IDaoWarehouse warehouseDao;

    public WarehouseServiceImpl(IDaoWarehouse warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @Override
    public Warehouse create(Warehouse warehouse) {
        return warehouseDao.create(warehouse);
    }

    @Override
    public Warehouse read(Long id) {
        return warehouseDao.read(id);
    }

    @Override
    public List<Warehouse> readAll() {
        return warehouseDao.readAll();
    }

    @Override
    public Warehouse update(Warehouse warehouse) {
        return warehouseDao.update(warehouse);
    }

    @Override
    public Long remove(Long id) {
        return warehouseDao.remove(id);
    }
}
