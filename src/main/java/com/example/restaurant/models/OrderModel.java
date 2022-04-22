package com.example.restaurant.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "Order", schema = "public")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderModel implements Serializable {

    @Id()
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "takeAway")
    private boolean takeAway;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;


    @Column(name = "total")
    private double total;

    @Column(name = "vat")
    private double vat;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "status")
    private String status;

}
