package com.example.restaurant.kafka;


import com.example.restaurant.avro.schema.OrderAvro;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.EventTypes;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.serviceimpl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class AvroConsumer {


    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @KafkaListener(topics = "#{'${avro.topic.name}'}")
    public void subscribe(OrderAvro orderAvro) throws IOException {
        log.info(String.format("Consumed Message -> %s", orderAvro));
        OrderModel orderModel = OrderMapper.OrderAvroToOrderModel(orderAvro);

        /**
         * applying event driven architecture using one operation which is
         * adding new order
         */

        if(String.valueOf(orderAvro.getEventType()).equals(String.valueOf(EventTypes.ORDER_CREATED))) {
            orderServiceImpl.addOrder(orderModel);
        }
    }
}
