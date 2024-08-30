package com.dproject.pizzeria.web.controller;

import com.dproject.pizzeria.persistence.entity.OrderEntity;
import com.dproject.pizzeria.persistence.projection.OrderSummary;
import com.dproject.pizzeria.services.OrderService;
import com.dproject.pizzeria.services.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders(){
        return ResponseEntity.ok(orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrder(@PathVariable Integer id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getOrderSummaryById(@PathVariable int id){
        return ResponseEntity.ok(this.orderService.getOrderSummaryById(id));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder (@RequestBody RandomOrderDto orderDto){
        return ResponseEntity.ok(this.orderService.saveRandomOrder(orderDto));
    }
}
