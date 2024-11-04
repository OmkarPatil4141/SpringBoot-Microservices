package com.omkar.microservices.order.service;

import com.omkar.microservices.order.client.InventoryClient;
import com.omkar.microservices.order.dto.OrderRequest;
import com.omkar.microservices.order.model.Order;
import com.omkar.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    //1. using mockito
    //2. using wiremock

    //inject the order-repository into order service class
    private final OrderRepository orderRepository;
    private  final InventoryClient inventoryClient;


    public void placeOrder(OrderRequest orderRequest)
    {
            var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());

            if(isProductInStock)
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

            else
            {
                throw new RuntimeException("Product with SkuCode "+ orderRequest.skuCode()+ " is not in stock");
            }

    }
}
