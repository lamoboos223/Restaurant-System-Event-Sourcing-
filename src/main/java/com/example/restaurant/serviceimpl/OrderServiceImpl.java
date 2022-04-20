package com.example.restaurant.serviceimpl;


import com.example.restaurant.avro.schema.OrderAvro;
import com.example.restaurant.kafka.AvroProducer;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.OrderModel;
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
import org.springframework.stereotype.Service;

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
    public OrderModel getOrderById(int id) {
        OrderModel orderModel = orderRepo.findById(id).get();

        OrderModel orderModel1 =  OrderModel.builder()
                .id(orderModel.getId())
                .takeAway(orderModel.isTakeAway())
                .items(orderModel.getItems())
                .status(String.valueOf(orderModel.getStatus()))
                .total(orderModel.getTotal())
                .vat(orderModel.getVat())
                .timestamp(orderModel.getTimestamp())
                .build();
        logger.warn(orderModel1.toString());
        return orderModel1;
//        return orderRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("id Resource %s not found ", id)));
    }

    @Override
    @CachePut(value = "Order")
    public OrderModel updateOrder(OrderModel orderModel, int id) {
//        OrderModel order = getOrderById(id);
//        order.setName(orderModel.getName());
//        order.setTotal(orderModel.getTotal());
//        order.setStatus(orderModel.getStatus());
//        return orderRepo.save(order);
        return orderModel;
    }

    @Override
    @CacheEvict(value = "Order")
    public void deleteOrder(int id) {
        OrderModel order = getOrderById(id);
        orderRepo.delete(order);
    }
}
