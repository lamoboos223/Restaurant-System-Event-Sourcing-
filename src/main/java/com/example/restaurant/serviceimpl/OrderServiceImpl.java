package com.example.restaurant.serviceimpl;


import com.example.restaurant.avro.schema.OrderAvro;
import com.example.restaurant.kafka.AvroProducer;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.EventTypes;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.models.OrderStatus;
import com.example.restaurant.repository.OrderRepo;
import com.example.restaurant.request.OrderRequest;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Autowired
    private final OrderRepo orderRepo;

    @Autowired
    private final AvroProducer avroProducer;


    @Override
    public void addOrder(OrderRequest orderRequest) {
        OrderModel orderModel = OrderMapper.orderRequestToOrderModel(orderRequest);
        OrderAvro orderAvro = OrderMapper.OrderModelToOrderAvro(orderModel);
        orderAvro.setEventType(String.valueOf(EventTypes.ORDER_CREATED));
        avroProducer.publish(orderAvro);
    }

    @Override
    public void addOrder(OrderModel orderModel) {
        orderRepo.save(orderModel);
    }

    @Override
    @Cacheable(value = "Order")
    public List<OrderModel> getOrder() {
        return orderRepo.findAll();
    }

    @Override
    @Cacheable(value = "Order")
    public OrderModel getOrderById(int id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("id Resource %s not found", id)));
    }

    @Override
    @CachePut(value = "Order")
    public OrderModel updateOrder(OrderRequest orderRequest, int id) {
        OrderModel orderModel = OrderMapper.orderRequestToOrderModel(orderRequest);
        OrderModel order = getOrderById(id);

        order.setTakeAway(orderModel.isTakeAway());
        order.setItems(orderModel.getItems());
        order.setTotal(orderModel.getTotal());
        order.setVat(orderModel.getVat());
        order.setStatus(String.valueOf(OrderStatus.PENDING));
        order.setTimestamp(orderModel.getTimestamp());

        OrderAvro orderAvro = OrderMapper.OrderModelToOrderAvro(orderModel);
        orderAvro.setEventType(String.valueOf(EventTypes.ORDER_UPDATED));
        avroProducer.publish(orderAvro);

        return orderRepo.save(order);
    }

    @Override
    @CacheEvict(value = "Order")
    public void deleteOrder(int id) {
        OrderModel orderModel = getOrderById(id);

        OrderAvro orderAvro = OrderMapper.OrderModelToOrderAvro(orderModel);
        orderAvro.setEventType(String.valueOf(EventTypes.ORDER_DELETED));
        avroProducer.publish(orderAvro);

        orderRepo.delete(orderModel);
    }

    @Override
    @CachePut(value = "Order")
    public OrderModel updateOrder(int id, OrderStatus orderStatus, EventTypes eventTypes) {
        OrderModel orderModel = getOrderById(id);

        OrderAvro orderAvro = OrderMapper.OrderModelToOrderAvro(orderModel);
        orderAvro.setEventType(String.valueOf(eventTypes));
        avroProducer.publish(orderAvro);

        orderModel.setStatus(String.valueOf(orderStatus));
        return orderModel;
    }
}
