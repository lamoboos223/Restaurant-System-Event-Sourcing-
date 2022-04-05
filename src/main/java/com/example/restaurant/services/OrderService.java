package com.example.restaurant.services;


import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<OrderModel> getOrder(){
        return orderRepo.findAll();
    }

    public Optional<OrderModel> getOrderById(Long id) {
        Optional<OrderModel> orderById = orderRepo.findById(id);
        if (orderById.isPresent()) {
            return orderRepo.findById(id);
        }
        else  {
//            TODO: use try and catch
            throw new RuntimeException("Order not found");
        }
    }

    public OrderModel updateOrder(OrderModel orderModel,Long id) {
//        TODO: use getOrderById instead of orderRepo since getOrderById handles the exception
        OrderModel order = orderRepo.findById(id).get();
            order.setName(orderModel.getName());
            order.setTotal(orderModel.getTotal());
            order.setStatus(orderModel.getStatus());
            return orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
//        TODO: use getOrderById instead of orderRepo since getOrderById handles the exception
        OrderModel order = orderRepo.findById(id).get();
        if (order != null) {
            orderRepo.delete(order);
        }
    }




}
