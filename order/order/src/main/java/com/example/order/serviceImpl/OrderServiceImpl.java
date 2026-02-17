package com.example.order.serviceImpl;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import com.example.order.exceptions.ResourceNotFoundException;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private ModelMapper mapper;

    public OrderServiceImpl(OrderRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setTotalPrice(orderDto.getPrice()*orderDto.getQuantity());
        Order save = this.repository.save(mapper.map(orderDto, Order.class));
        return mapper.map(save,OrderDto.class);
    }

    @Override
    public OrderDto get(Long id) {
        Order order = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        OrderDto existingProduct = get(orderId);

        existingProduct.setProductId(orderDto.getProductId());
        existingProduct.setQuantity(orderDto.getQuantity());
        existingProduct.setTotalPrice(orderDto.getPrice()*orderDto.getQuantity());

        Order save = this.repository.save(mapper.map(existingProduct, Order.class));
        return mapper.map(save, OrderDto.class);
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, String status) {
        OrderDto existingProduct = get(orderId);

        existingProduct.setOrderStatus(status);
        Order save = this.repository.save(mapper.map(existingProduct, Order.class));
        return mapper.map(save, OrderDto.class);
    }

    @Override
    public void deleteOrder(Long id) {
        this.repository.deleteById(id);
    }
}
