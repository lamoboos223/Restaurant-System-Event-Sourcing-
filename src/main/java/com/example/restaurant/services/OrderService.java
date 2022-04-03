package com.example.restaurant.services;


import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;


    public OrderModel addOrder(OrderModel orderModel){
        return orderRepo.save(orderModel);
    }


}
