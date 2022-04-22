package com.example.restaurant.controllers;


import com.example.restaurant.models.EventTypes;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.models.OrderStatus;
import com.example.restaurant.request.OrderRequest;
import com.example.restaurant.response.OrderResponse;
import com.example.restaurant.serviceimpl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private final OrderServiceImpl orderServiceImpl;

    @PostMapping()
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest){
        orderServiceImpl.addOrder(orderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public List<OrderModel> getAllOrder(){
        return orderServiceImpl.getOrder();
    }


    @GetMapping("/{id}")
    public OrderModel getOrderById(@PathVariable("id") int id){
        return orderServiceImpl.getOrderById(id);
//        return OrderMapper.orderModelToOrderResponse(orderServiceImpl.getOrderById(id));
    }


    @PutMapping("/{id}")
    public OrderModel updateOrder(@RequestBody OrderRequest orderRequest , @PathVariable("id") int id ) {
        return orderServiceImpl.updateOrder(orderRequest,id);
    }

    @PatchMapping("/cancel/{id}")
    public OrderModel cancelOrder(@PathVariable("id") int id ) {
        return orderServiceImpl.updateOrder(id, OrderStatus.CANCELLED, EventTypes.ORDER_CANCELLED);
    }
    @PatchMapping("/served/{id}")
    public OrderModel servedOrder(@PathVariable("id") int id ) {
        return orderServiceImpl.updateOrder(id, OrderStatus.SERVED, EventTypes.ORDER_SERVED);
    }
    @PatchMapping("/accept/{id}")
    public OrderModel acceptOrder(@PathVariable("id") int id ) {
        return orderServiceImpl.updateOrder(id, OrderStatus.SERVING, EventTypes.ORDER_SERVING);
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") int id ) {
        orderServiceImpl.deleteOrder(id);
    }
}
