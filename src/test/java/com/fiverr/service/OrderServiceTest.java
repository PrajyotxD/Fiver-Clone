package com.fiverr.service;

import com.fiverr.model.Order;
import com.fiverr.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setStatus("NEW"); // initial status

        when(orderRepository.save(any(Order.class))).thenAnswer(i -> {
            Order o = i.getArgument(0);
            o.setStatus("PENDING"); // status set by the service
            return o;
        });

        Order savedOrder = orderService.createOrder(order);

        assertEquals("PENDING", savedOrder.getStatus());
        verify(orderRepository).save(order);
    }
}
