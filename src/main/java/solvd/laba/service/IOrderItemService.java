package solvd.laba.service;

import solvd.laba.model.OrderItem;

import java.util.List;


public interface IOrderItemService {

    OrderItem readByIds(Long orderId, Long productId);
    OrderItem updateByOrder(OrderItem entity, Long orderId);
    Boolean removeByIds(Long orderId, Long productId);
    Boolean removeByOrder(Long orderId);
    Boolean removeByProduct(Long productId);
    List<OrderItem> readByOrder(Long orderId);
    OrderItem createByOrder(OrderItem entity, Long orderId);
}

