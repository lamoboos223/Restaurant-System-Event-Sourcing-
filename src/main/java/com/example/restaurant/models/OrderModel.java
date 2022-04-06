package com.example.restaurant.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


@Table(name = "Order", schema = "public")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {

    @Id()
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this is to configure the id to be auto increment
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Total")
    private BigDecimal total;

    @Column(name = "Status")
    private String status;

}
