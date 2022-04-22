package com.example.restaurant.service;

import com.example.restaurant.models.EventTypes;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.models.OrderStatus;
import com.example.restaurant.request.OrderRequest;

import java.util.List;

public interface OrderService {

    /**
     * This method will be used by the kafka producer to publish messages to kafka topic
     * @param orderRequest
     */
    void addOrder(OrderRequest orderRequest);

    /**
     * This method will be used by the kafka consumer to save the orderModel to the db
     * @param orderModel
     */
    void addOrder(OrderModel orderModel);

    List<OrderModel> getOrder();

    OrderModel getOrderById(int id);

    OrderModel updateOrder(OrderRequest orderRequest, int id);

    void deleteOrder(int id);

    OrderModel updateOrder(int id, OrderStatus orderStatus, EventTypes eventTypes);

}
