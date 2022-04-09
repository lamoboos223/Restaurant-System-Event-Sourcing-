package com.example.restaurant.controllers;


import com.example.restaurant.avro.schema.orders;
import com.example.restaurant.kafka.AvroProducer;
import com.example.restaurant.models.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/avro/orders")
public class AvroOrdersController {


    Logger logger = LoggerFactory.getLogger(AvroOrdersController.class);


    @Autowired
    private AvroProducer avroProducer;

    @PostMapping
    public void send(@RequestBody OrderModel orderModel){

        orders order = orders.newBuilder().build();
        order.setId(orderModel.getId());
        order.setName(orderModel.getName());
        order.setTotal(orderModel.getTotal());
        order.setStatus(orderModel.getStatus());

        avroProducer.send(order);

    }
}
