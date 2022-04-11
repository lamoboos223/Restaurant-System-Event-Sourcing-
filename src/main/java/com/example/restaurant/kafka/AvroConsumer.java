package com.example.restaurant.kafka;


import com.example.restaurant.avro.schema.orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class AvroConsumer {

    Logger logger = LoggerFactory.getLogger(AvroConsumer.class);

    @KafkaListener(topics = "#{'${avro.topic.name}'}")
    public void subscribe(orders order) throws IOException {
        logger.info(String.format("Consumed message -> %s", order));

//        TODO: save in db
    }
}
