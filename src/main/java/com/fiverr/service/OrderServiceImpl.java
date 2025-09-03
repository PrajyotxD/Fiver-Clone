package com.fiverr.service;

import com.fiverr.model.Order;
import com.fiverr.model.User;
import com.fiverr.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        // In a real application, you would add logic to handle payment and stock.
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByBuyer(User buyer) {
        return orderRepository.findByBuyer(buyer);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order updateOrderStatus(Order order, String status) {
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
