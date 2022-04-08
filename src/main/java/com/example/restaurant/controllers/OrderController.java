package com.example.restaurant.controllers;


import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.request.OrderRequest;
import com.example.restaurant.response.OrderResponse;
import com.example.restaurant.serviceimpl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderServiceImpl.addOrder(OrderMapper.orderRequestToOrderModel(orderRequest)));

    }

    @GetMapping()
    public List<OrderModel> getAllOrder(){
        return orderServiceImpl.getOrder();
    }


    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable("id") long id){
        return OrderMapper.orderToResponse(orderServiceImpl.getOrderById(id));
    }


    @PutMapping("/{id}")
    public OrderModel updateOrder(@RequestBody OrderModel orderModel , @PathVariable("id") long id ) {
        return orderServiceImpl.updateOrder(orderModel,id);
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long id ) {
        orderServiceImpl.deleteOrder(id);
    }
}
