package com.example.restaurant.serviceimpl;


import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.repository.OrderRepo;
import com.example.restaurant.response.OrderResponse;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
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

    private final OrderRepo orderRepo;

    @Override
    public OrderResponse addOrder(OrderModel orderModel) {
        return OrderMapper.orderToResponse(orderRepo.save(orderModel));
    }

    @Override
    @Cacheable(value = "Order")
    public List<OrderModel> getOrder() {
        return orderRepo.findAll();
    }

    @Override
    @Cacheable(value = "Order")
    public OrderModel getOrderById(long id) {
        return orderRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(" id Resource %s not found ", id)));
    }

    @Override
    @CachePut(value = "Order")
    public OrderModel updateOrder(OrderModel orderModel, long id) {
        OrderModel order = getOrderById(id);
        order.setName(orderModel.getName());
        order.setTotal(orderModel.getTotal());
        order.setStatus(orderModel.getStatus());
        return orderRepo.save(order);
    }

    @Override
    @CacheEvict(value = "Order")
    public void deleteOrder(long id) {
        OrderModel order = getOrderById(id);
        orderRepo.delete(order);
    }


}
