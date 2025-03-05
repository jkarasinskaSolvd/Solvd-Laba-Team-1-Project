package solvd.laba.service.impl;

import solvd.laba.idao.IDaoOrder;
import solvd.laba.model.Order;
import solvd.laba.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {

    private final IDaoOrder daoOrder;

    public OrderServiceImpl(IDaoOrder daoOrder) {
        this.daoOrder = daoOrder;
    }

    @Override
    public Order create(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        return daoOrder.create(order);
    }

    @Override
    public Order read(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        return daoOrder.read(id);
    }

    @Override
    public List<Order> readAll() {
        return daoOrder.readAll();
    }

    @Override
    public Order update(Order order) {
        if (order == null || order.getId() == null) {
            throw new IllegalArgumentException("Invalid order or order ID");
        }
        return daoOrder.update(order);
    }

    @Override
    public Long remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        return daoOrder.remove(id);
    }
}
