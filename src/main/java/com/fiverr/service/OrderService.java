package com.fiverr.service;

import com.fiverr.model.Order;
import com.fiverr.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getOrdersByBuyer(User buyer);
    Optional<Order> getOrderById(Long id);
    Order updateOrderStatus(Order order, String status);
}
