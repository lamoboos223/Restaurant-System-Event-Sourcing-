package com.example.restaurant.kafka;


import com.example.restaurant.avro.schema.OrderAvro;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.serviceimpl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AvroConsumer {

    Logger logger = LoggerFactory.getLogger(AvroConsumer.class);

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @KafkaListener(topics = "#{'${avro.topic.name}'}")
    public void subscribe(OrderAvro orderAvro) throws IOException {
        logger.info(String.format("Consumed Message -> %s", orderAvro));
        OrderModel orderModel = OrderMapper.OrderAvroToOrderModel(orderAvro);
        orderServiceImpl.addOrder(orderModel);
    }
}
