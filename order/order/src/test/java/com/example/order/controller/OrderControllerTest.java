package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderDto getSampleOrderDto() {
        OrderDto dto = new OrderDto();
        dto.setOrderId(1L);
        dto.setProductId(1L);
        dto.setQuantity(2);
        dto.setOrderStatus("CREATED");
        dto.setPrice(125.10);
        return dto;
    }

    @Test
    void testCreateOrder() throws Exception {

        OrderDto dto = getSampleOrderDto();

        Mockito.when(orderService.createOrder(any(OrderDto.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productName").value("Laptop"));
    }

    @Test
    void testUpdateOrder() throws Exception {

        OrderDto dto = getSampleOrderDto();

        Mockito.when(orderService.updateOrder(eq(1L), any(OrderDto.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    void testGetOrder() throws Exception {

        OrderDto dto = getSampleOrderDto();

        Mockito.when(orderService.get(1L)).thenReturn(dto);

        mockMvc.perform(get("/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Laptop"));
    }

    @Test
    void testUpdateOrderStatus() throws Exception {

        OrderDto dto = getSampleOrderDto();
        dto.setOrderStatus("SHIPPED");

        Mockito.when(orderService.updateOrderStatus(1L, "SHIPPED"))
                .thenReturn(dto);

        mockMvc.perform(patch("/v1/orders/1/SHIPPED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SHIPPED"));
    }

    @Test
    void testDeleteOrder() throws Exception {

        Mockito.doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/v1/orders/1"))
                .andExpect(status().isNoContent());
    }
}