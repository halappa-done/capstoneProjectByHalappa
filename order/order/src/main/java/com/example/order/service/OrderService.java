package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;

public interface OrderService {

   OrderDto createOrder(OrderDto order);

   OrderDto get(Long id);

   OrderDto updateOrder(Long orderId, OrderDto orderDto);

   OrderDto updateOrderStatus(Long orderId, String statusString );

   void deleteOrder(Long id);

}
