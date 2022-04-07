package com.example.restaurant.repository;

import com.example.restaurant.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Responsble for data access 
public interface OrderRepo extends JpaRepository<OrderModel, Long> {
}
