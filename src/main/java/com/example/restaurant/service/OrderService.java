package com.example.restaurant.service;

import com.example.restaurant.models.OrderModel;
import com.example.restaurant.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse addOrder(OrderModel orderModel);

    List<OrderModel> getOrder();

    OrderModel getOrderById(int id);

    OrderModel updateOrder(OrderModel orderModel, int id);

    void deleteOrder(int id);
}
