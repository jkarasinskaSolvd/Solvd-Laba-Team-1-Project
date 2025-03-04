package solvd.laba.service.impl;

import solvd.laba.idao.IDaoOrderItem;
import solvd.laba.model.OrderItem;
import solvd.laba.service.IOrderItemService;

import java.util.List;


public class OrderItemServiceImpl implements IOrderItemService {

    private final IDaoOrderItem daoOrderItem;

    public OrderItemServiceImpl(IDaoOrderItem daoOrderItem) {
        this.daoOrderItem = daoOrderItem;
    }

    @Override
    public OrderItem createByOrder(OrderItem orderItem, Long orderId) {
        if (orderItem == null || orderId == null) {
            throw new IllegalArgumentException("Invalid OrderItem or order ID");
        }
        return daoOrderItem.createByOrder(orderItem, orderId);
    }

    @Override
    public OrderItem readByIds(Long orderId, Long productId) {
        if (orderId == null || productId == null) {
            throw new IllegalArgumentException("Invalid IDs");
        }
        return daoOrderItem.readByIds(orderId, productId);
    }


    @Override
    public OrderItem updateByOrder(OrderItem orderItem, Long orderId) {
        if (orderItem == null || orderId == null) {
            throw new IllegalArgumentException("Invalid OrderItem or order ID");
        }
        return daoOrderItem.updateByOrder(orderItem, orderId);
    }

    @Override
    public Boolean removeByIds(Long orderId, Long productId) {
        if (orderId == null || productId == null) {
            throw new IllegalArgumentException("Invalid IDs");
        }
        return daoOrderItem.removeByIds(orderId, productId);
    }

    @Override
    public Boolean removeByOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        return daoOrderItem.removeByOrder(orderId);
    }

    @Override
    public Boolean removeByProduct(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        return daoOrderItem.removeByProduct(productId);
    }

    @Override
    public List<OrderItem> readByOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        return daoOrderItem.readByOrder(orderId);
    }
}
