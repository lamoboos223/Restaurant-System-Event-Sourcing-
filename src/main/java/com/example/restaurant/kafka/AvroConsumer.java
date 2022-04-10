package com.example.restaurant.kafka;


import com.example.restaurant.avro.schema.orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;

@Service
public class AvroConsumer {

    Logger logger = LoggerFactory.getLogger(AvroConsumer.class);

    @Value("${avro.topic.name}")
    private static final String topic = "";

    @Autowired
    private KafkaTemplate<String, orders> kafkaTemplate;

    @KafkaListener(topics = "restaurant-orders")
    public void subscribe(orders order) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", order));
    }
}
