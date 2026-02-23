package com.example.order.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import com.example.order.exceptions.ResourceNotFoundException;
import com.example.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository repository;
    private ModelMapper mapper = new ModelMapper();
    @InjectMocks
    private OrderServiceImpl service;

    private Order order;
    private OrderDto orderDto;

    @BeforeEach
    void setUp() {
        service = new OrderServiceImpl(repository, mapper);

        order = new Order();
        order.setOrderId(1L);
        order.setProductId(101L);
        order.setPrice(100.0);
        order.setQuantity(2);
        order.setTotalPrice(200.0);
        order.setOrderStatus("CREATED");

        orderDto = mapper.map(order, OrderDto.class);
    }

    // ✅ createOrder
    @Test
    void testCreateOrder() {

        when(repository.save(any(Order.class))).thenReturn(order);
        OrderDto result = service.createOrder(orderDto);
        assertNotNull(result);
        assertEquals(200.0, result.getTotalPrice());
        verify(repository, times(1)).save(any(Order.class));
    }

    // ✅ get - success
    @Test
    void testGetOrderSuccess() {

        when(repository.findById(1L)).thenReturn(Optional.of(order));
        OrderDto result = service.get(1L);
        assertNotNull(result);
        assertEquals(101L, result.getProductId());
        verify(repository).findById(1L);
    }

    // ✅ get - not found
    @Test
    void testGetOrderNotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> service.get(1L));
        verify(repository).findById(1L);
    }

    // ✅ updateOrder
    @Test
    void testUpdateOrder() {

        when(repository.findById(1L)).thenReturn(Optional.of(order));
        when(repository.save(any(Order.class))).thenReturn(order);

        orderDto.setQuantity(3);
        orderDto.setPrice(100.0);

        OrderDto result = service.updateOrder(1L, orderDto);

        assertEquals(300.0, result.getTotalPrice());
        verify(repository).save(any(Order.class));
    }

    // ✅ updateOrderStatus
    @Test
    void testUpdateOrderStatus() {

        when(repository.findById(1L)).thenReturn(Optional.of(order));
        when(repository.save(any(Order.class))).thenReturn(order);

        OrderDto result = service.updateOrderStatus(1L, "SHIPPED");

        assertEquals("SHIPPED", result.getOrderStatus());
        verify(repository).save(any(Order.class));
    }

    // ✅ deleteOrder
    @Test
    void testDeleteOrder() {
        doNothing().when(repository).deleteById(1L);
        service.deleteOrder(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}