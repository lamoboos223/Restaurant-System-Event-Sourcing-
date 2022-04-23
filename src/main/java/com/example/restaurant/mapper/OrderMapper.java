package com.example.restaurant.mapper;

import com.example.restaurant.avro.schema.ItemAvro;
import com.example.restaurant.avro.schema.OrderAvro;
import com.example.restaurant.models.Item;
import com.example.restaurant.models.OrderModel;
import com.example.restaurant.models.OrderStatus;
import com.example.restaurant.request.OrderRequest;
import com.example.restaurant.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class OrderMapper {


    public static OrderModel orderRequestToOrderModel(OrderRequest orderRequest) {
        double total = 0;
        for (Item itemModel : orderRequest.getItems() ){
            total += itemModel.getPrice();
        }
        double vat = 0.15 * total; // vat is 15% of the total order

       return OrderModel.builder()
               .takeAway(orderRequest.isTakeAway())
               .items(orderRequest.getItems())
               .status(String.valueOf(OrderStatus.PENDING))
               .total(total)
               .vat(vat)
               .timestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()))
               .build();
    }

    public static OrderResponse orderModelToOrderResponse(OrderModel orderModel) {

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(String.valueOf(orderModel.getId()));
        orderResponse.setTakeAway(String.valueOf(orderModel.isTakeAway()));
        orderResponse.setItems(orderModel.getItems());
        orderResponse.setStatus(orderModel.getStatus());
        orderResponse.setTotal(String.valueOf(orderModel.getTotal()));
        orderResponse.setVat(String.valueOf(orderModel.getVat()));
        orderResponse.setTimestamp(orderModel.getTimestamp());

        return orderResponse;
    }

    public static OrderAvro OrderModelToOrderAvro(OrderModel orderModel){
        List<ItemAvro> itemAvroList = new ArrayList<>();
        for(Item i : orderModel.getItems()){
            itemAvroList.add(new ItemAvro(i.getId(), i.getName(), i.getPrice()));
        }
        OrderAvro orderAvro = OrderAvro.newBuilder().build();
        orderAvro.setItems(itemAvroList);
        orderAvro.setTakeAway(orderModel.isTakeAway());
        orderAvro.setStatus(orderModel.getStatus());
        orderAvro.setTotal(orderModel.getTotal());
        orderAvro.setVat(orderModel.getVat());
        orderAvro.setTimestamp(orderModel.getTimestamp());
        return orderAvro;
    }

    public static OrderModel OrderAvroToOrderModel(OrderAvro orderAvro){

        List<Item> itemList = new ArrayList<>();
        for(ItemAvro i : orderAvro.getItems()){
            Item item = new Item();
            item.setName(String.valueOf(i.getName()));
            item.setPrice(i.getPrice());
            itemList.add(item);
        }

        return OrderModel.builder()
                .takeAway(orderAvro.getTakeAway())
                .items(itemList)
                .status(String.valueOf(orderAvro.getStatus()))
                .total(orderAvro.getTotal())
                .vat(orderAvro.getVat())
                .timestamp(String.valueOf(orderAvro.getTimestamp()))
                .build();
    }
}
