package solvd.laba.service.impl;

import solvd.laba.idao.IDaoOrderItem;
import solvd.laba.model.OrderItem;
import solvd.laba.service.IOrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements IOrderItemService{

    private final IDaoOrderItem orderItemDao;
    public OrderItemServiceImpl(IDaoOrderItem orderItemDao){
        this.orderItemDao=orderItemDao;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return orderItemDao.create(orderItem);
    }

    @Override
    public OrderItem read(Long id) {
        return orderItemDao.read(id);
    }

    @Override
    public List<OrderItem> readAll() {
        return orderItemDao.readAll();
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return orderItemDao.update(orderItem);
    }

    @Override
    public Long remove(Long id) {
        return orderItemDao.remove(id);
    }
}
