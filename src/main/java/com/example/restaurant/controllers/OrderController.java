package com.example.restaurant.controllers;


import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repositories.OrderRepo;
import com.example.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(path = "/orders")
public class OrderController {

//    TODO: use ResponseEntity Class to form the response you
//     will return to user instead or returning the model orderModel as is
//    https://www.baeldung.com/spring-response-entity


    @Autowired
    private OrderService orderService;

    // Create a new order
    @PostMapping
    public OrderModel addOrder(@RequestBody OrderModel orderModel){
        return orderService.addOrder(orderModel);
    }

    // Get all orders
    @GetMapping
    public List<OrderModel> getAllOrder(){
        return orderService.getOrder();
    }

    // Get order by id
    @GetMapping("/{id}")
    public Optional<OrderModel> getOrderById(@PathVariable("id") long id){
        return orderService.getOrderById(id);
    }


    // Update the order
    @PutMapping("/{id}")
    public OrderModel updateOrder(@RequestBody OrderModel orderModel ,@PathVariable("id") long id ) {
        return orderService.updateOrder(orderModel,id);
    }

    // Update the order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long id ) {
        orderService.deleteOrder(id);
    }


}
