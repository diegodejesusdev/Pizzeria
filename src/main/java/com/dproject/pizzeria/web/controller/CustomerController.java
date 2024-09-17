package com.dproject.pizzeria.web.controller;

import com.dproject.pizzeria.persistence.entity.CustomerEntity;
import com.dproject.pizzeria.persistence.entity.OrderEntity;
import com.dproject.pizzeria.services.CustomerService;
import com.dproject.pizzeria.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(customerService.findByPhone(phone));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable Integer id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }
}
