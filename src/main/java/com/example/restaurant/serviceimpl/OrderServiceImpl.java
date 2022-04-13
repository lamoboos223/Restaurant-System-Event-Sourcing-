package com.example.restaurant.serviceimpl;


import com.example.restaurant.avro.schema.orders;
import com.example.restaurant.kafka.AvroProducer;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repository.OrderRepo;
import com.example.restaurant.request.OrderRequest;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
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

    @Autowired
    private final OrderRepo orderRepo;

    @Autowired
    private final AvroProducer avroProducer;


    /**
     * This method will be used by the kafka producer to publish messages to kafka topic
     *
     * @param orderRequest
     */
    @Override
    public void addOrder(OrderRequest orderRequest) {
        OrderModel orderModel = OrderMapper.orderRequestToOrderModel(orderRequest);
        orders order = orders.newBuilder().build();
        order.setName(orderModel.getName());
        order.setTotal(orderModel.getTotal());
        order.setStatus(orderModel.getStatus());
        avroProducer.publish(order);
    }

    /**
     * This method will be used by the kafka consumer to save the orderModel to the db
     *
     * @param orderModel
     */
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
        return orderRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("id Resource %s not found ", id)));
    }

    @Override
    @CachePut(value = "Order")
    public OrderModel updateOrder(OrderModel orderModel, int id) {
        OrderModel order = getOrderById(id);
        order.setName(orderModel.getName());
        order.setTotal(orderModel.getTotal());
        order.setStatus(orderModel.getStatus());
        return orderRepo.save(order);
    }

    @Override
    @CacheEvict(value = "Order")
    public void deleteOrder(int id) {
        OrderModel order = getOrderById(id);
        orderRepo.delete(order);
    }
}
