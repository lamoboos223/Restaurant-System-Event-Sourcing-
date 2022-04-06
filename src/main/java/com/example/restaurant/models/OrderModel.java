package com.example.restaurant.models;


import javax.persistence.*;
import java.io.Serializable;


@Table(name = "Order", schema = "public")
@Entity
public class OrderModel implements Serializable {

    @Id()
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this is to configure the id to be auto increment
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Total")
    private double total;

    @Column(name = "Status")
    private String status;


    public OrderModel() {
    }

    public OrderModel(String name , double total , String status ) {
        this.name = name;
        this.total = total;
        this.status = status;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
