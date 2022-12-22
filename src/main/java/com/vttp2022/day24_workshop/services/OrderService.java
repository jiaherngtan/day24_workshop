package com.vttp2022.day24_workshop.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vttp2022.day24_workshop.models.Order;
import com.vttp2022.day24_workshop.repositories.OrderRepository;
import com.vttp2022.day24_workshop.repositories.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(rollbackFor = OrderException.class)
    public void createOrder(Order o) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        System.out.println(orderId);
        o.setOrderId(orderId);
        orderRepository.addOrder(o);
        productRepository.addProduct(o.getProducts(), orderId);
    }

}
