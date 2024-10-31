package com.omkar.microservices.order.service;

import com.omkar.microservices.order.dto.OrderRequest;
import com.omkar.microservices.order.model.Order;
import com.omkar.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    //inject the order-repository into order service class
    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest)
    {
            //map order request to order object

            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());

            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            //save order to orderrepository

        orderRepository.save(order);

    }
}
