package com.example.restaurant.serviceimpl;


import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repository.OrderRepo;
import com.example.restaurant.response.OrderResponse;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public OrderResponse addOrder(OrderModel orderModel){
        return OrderMapper.orderToResponse(orderRepo.save(orderModel));
    }

    @Override
    public List<OrderModel> getOrder(){
        return orderRepo.findAll();
    }

    @Override
    public OrderModel getOrderById(long id) {
        return orderRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(" id Resource %s not found ",id)));
    }

    @Override
    public OrderModel updateOrder(OrderModel orderModel,long id) {
//        TODO: use getOrderById instead of orderRepo since getOrderById handles the exception
        OrderModel order = orderRepo.findById(id).get();
            order.setName(orderModel.getName());
            order.setTotal(orderModel.getTotal());
            order.setStatus(orderModel.getStatus());
            return orderRepo.save(order);
    }

    @Override
    public void deleteOrder(long id) {
//        TODO: use getOrderById instead of orderRepo since getOrderById handles the exception
        OrderModel order = orderRepo.findById(id).get();
        if (order != null) {
            orderRepo.delete(order);
        }
    }




}
