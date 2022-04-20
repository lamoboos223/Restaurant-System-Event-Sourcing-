package com.example.restaurant.response;

import com.example.restaurant.models.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private String id;
    private String takeAway;
    private List<Item> items;
    private String total;
    private String vat;
    private String status;
    private String timestamp;
}
