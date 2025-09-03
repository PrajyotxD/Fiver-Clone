package com.fiverr.controller;

import com.fiverr.model.Order;
import com.fiverr.model.User;
import com.fiverr.repository.UserRepository;
import com.fiverr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/by-buyer/{buyerId}")
    public ResponseEntity<List<Order>> getOrdersByBuyer(@PathVariable Long buyerId) {
        Optional<User> user = userRepository.findById(buyerId);
        if (user.isPresent()) {
            return ResponseEntity.ok(orderService.getOrdersByBuyer(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        return orderService.getOrderById(id)
                .map(order -> {
                    // In a real app, you'd have more robust status change logic
                    // and authorization checks.
                    if ("ACCEPTED".equals(status) || "REJECTED".equals(status)) {
                        return ResponseEntity.ok(orderService.updateOrderStatus(order, status));
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
