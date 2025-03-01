package solvd.laba.service.impl;

import solvd.laba.idao.IDaoOrder;
import solvd.laba.model.Order;
import solvd.laba.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {

    private final IDaoOrder orderDao;
    public OrderServiceImpl(IDaoOrder orderDao){
        this.orderDao=orderDao;
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order read(Long id) {
        return orderDao.read(id);
    }

    @Override
    public List<Order> readAll() {
        return orderDao.readAll();
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Long remove(Long id) {
        return orderDao.remove(id);
    }
}
