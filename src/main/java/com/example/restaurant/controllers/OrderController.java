package com.example.restaurant.controllers;


import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.request.OrderRequest;
import com.example.restaurant.response.OrderResponse;
import com.example.restaurant.serviceimpl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// TODO: since all the methods uses /order put this mapping on the class instead of the method
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

//    TODO: use ResponseEntity Class to form the response you
//     will return to user instead or returning the model orderModel as is
//    https://www.baeldung.com/spring-response-entity

    private final OrderServiceImpl orderServiceImpl;

    // Create a new order
    @PostMapping()
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest){
//        return orderService.addOrder(new OrderModel(orderModel.getName(), orderModel.getTotal(), orderModel.getStatus()));
        return ResponseEntity.ok(orderServiceImpl.addOrder(OrderMapper.orderRequestToOrderModel(orderRequest)));

    }

    // Get all orders
    @GetMapping()
    public List<OrderModel> getAllOrder(){
        return orderServiceImpl.getOrder();
    }

    // Get order by id
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable("id") long id){
        return OrderMapper.orderToResponse(orderServiceImpl.getOrderById(id));
    }

//    TODO: no need for id
    // Update the order
    @PutMapping("/{id}")
    public OrderModel updateOrder(@RequestBody OrderModel orderModel , @PathVariable("id") long id ) {
        return orderServiceImpl.updateOrder(orderModel,id);
    }

    // Update the order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long id ) {
        orderServiceImpl.deleteOrder(id);
    }


}
