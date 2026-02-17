package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping()
    ResponseEntity<OrderDto> createOrder  (@Valid @RequestBody OrderDto orderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createOrder(orderDto));
    }
    @PutMapping("/{id}")
    ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto){
        return ResponseEntity.ok().body(this.service.updateOrder(id, orderDto));
    }
    @GetMapping("/{id}")
    ResponseEntity<OrderDto> get(@PathVariable Long id){

        return ResponseEntity.ok().body(this.service.get(id));
    }

    @PatchMapping("/{id}/{status}")
    ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @PathVariable String status){
        return ResponseEntity.ok().body(this.service.updateOrderStatus(id, status));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        this.service.deleteOrder(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
