package com.example.restaurant.services;


import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;


    public OrderModel addOrder(OrderModel orderModel){
        return orderRepo.save(orderModel);
    }

    @Cacheable(value = "Order")
    public List<OrderModel> getOrder(){
        return orderRepo.findAll();
    }

    @Cacheable(value = "Order", key = "#id")
    public Optional<OrderModel> getOrderById(Long id) {
        Optional<OrderModel> orderById = orderRepo.findById(id);
        if (orderById.isPresent()) {
            return orderRepo.findById(id);
//            TODO: use this exception throwing ^_^
//            return orderRepo.findById(id).orElseThrow(RuntimeException::new);
        }
        else  {
//            TODO: use try and catch
            throw new RuntimeException("Order not found");
        }
    }

    @CachePut(value = "Order", key = "#id")
    public OrderModel updateOrder(OrderModel orderModel,Long id) {
//        TODO: use getOrderById instead of orderRepo since getOrderById handles the exception
        OrderModel order = orderRepo.findById(id).get();
            order.setName(orderModel.getName());
            order.setTotal(orderModel.getTotal());
            order.setStatus(orderModel.getStatus());
            return orderRepo.save(order);
    }

    @CacheEvict(value = "Order")
    public void deleteOrder(Long id) {
//        TODO: use getOrderById instead of orderRepo since getOrderById handles the exception
        OrderModel order = orderRepo.findById(id).get();
        if (order != null) {
            orderRepo.delete(order);
        }
    }




}
