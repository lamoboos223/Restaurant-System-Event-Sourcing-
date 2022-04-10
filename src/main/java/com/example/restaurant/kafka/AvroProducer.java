package com.example.restaurant.kafka;


import com.example.restaurant.avro.schema.orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.kafka.support.SendResult;

@Service
public class AvroProducer {


    Logger logger = LoggerFactory.getLogger(AvroProducer.class);
    @Value("${avro.topic.name}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, orders> kafkaTemplate;

    public void publish(orders order){
        ListenableFuture<SendResult<String, orders>> future = kafkaTemplate.send(topic, String.valueOf(order.getId()), order);

        future.addCallback(new ListenableFutureCallback<SendResult<String, orders>>() {
            @Override
            public void onFailure(Throwable ex) {

                logger.warn(String.format("Failed sending Message %s to topic %s", order, topic));
                logger.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, orders> result) {
                logger.info(String.format("Successful sending Message %s to topic %s", order, topic));
            }
        });


    }

}
