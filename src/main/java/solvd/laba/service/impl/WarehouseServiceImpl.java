package solvd.laba.service.impl;

import solvd.laba.idao.IDaoWarehouse;
import solvd.laba.model.Warehouse;
import solvd.laba.service.IWarehouseService;

import java.util.List;

public class WarehouseServiceImpl implements IWarehouseService {
    private final IDaoWarehouse daoWarehouse;
    public WarehouseServiceImpl(IDaoWarehouse daoWarehouse) {
        this.daoWarehouse = daoWarehouse;
    }

    @Override
    public Warehouse create(Warehouse warehouse) {
        if (warehouse == null) {
            throw new IllegalArgumentException("Warehouse cannot be null");
        }
        return daoWarehouse.create(warehouse);
    }

    @Override
    public Warehouse read(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid warehouse ID");
        }
        return daoWarehouse.read(id);
    }

    @Override
    public List<Warehouse> readAll() {
        return daoWarehouse.readAll();
    }

    @Override
    public Warehouse update(Warehouse warehouse) {
        if (warehouse == null || warehouse.getId() == null) {
            throw new IllegalArgumentException("Invalid warehouse or warehouse ID");
        }
        return daoWarehouse.update(warehouse);
    }

    @Override
    public Long remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid warehouse ID");
        }
        return daoWarehouse.remove(id);
    }
}
