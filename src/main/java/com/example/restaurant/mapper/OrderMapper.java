package com.example.restaurant.mapper;

import com.example.restaurant.models.OrderModel;
import com.example.restaurant.request.OrderRequest;
import com.example.restaurant.response.OrderResponse;

public class OrderMapper {

    public static OrderModel orderRequestToOrderModel(OrderRequest orderRequest) {
       return OrderModel.builder()
                .name(orderRequest.getName())
                .total(orderRequest.getTotal())
                .status(orderRequest.getStatus())
                .build();
    }

    public static OrderResponse orderToResponse(OrderModel orderModel) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(String.valueOf(orderModel.getId()));
        orderResponse.setName(orderModel.getName());
        orderResponse.setTotal(String.valueOf(orderModel.getTotal()));
        orderResponse.setStatus(orderModel.getStatus());
        return orderResponse;
    }
}
