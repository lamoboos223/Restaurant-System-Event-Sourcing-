package com.example.restaurant.controllers;


import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repositories.OrderRepo;
import com.example.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepo repo;


    @PostMapping(path = "/orders")
    public OrderModel addOrder(@RequestBody OrderModel orderModel){
        return orderService.addOrder(orderModel);
        // return this.repo.save(orderModel);
    }

    @GetMapping("/orders")
    public List<OrderModel> getAllOrder(){
        return this.repo.findAll();
    }

    @GetMapping("/orderss")
    public String getAllOrder1(){
        return "Orders controller is working";
    }


}
