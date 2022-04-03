package com.example.restaurant.controllers;


import com.example.restaurant.models.OrderModel;
import com.example.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/orders")
    public OrderModel addOrder(@RequestBody OrderModel orderModel){
        return orderService.addOrder(orderModel);
    }
}
