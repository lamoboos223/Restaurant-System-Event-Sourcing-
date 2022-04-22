package com.example.restaurant.request;

import com.example.restaurant.models.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private boolean takeAway;
    private List<Item> items;
}
