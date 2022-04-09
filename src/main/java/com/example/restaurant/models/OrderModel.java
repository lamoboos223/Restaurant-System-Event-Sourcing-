package com.example.restaurant.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Table(name = "Order", schema = "public")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel implements Serializable {

    @Id()
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this is to configure the id to be auto increment
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Total")
    private double total;

    @Column(name = "Status")
    private String status;

}
