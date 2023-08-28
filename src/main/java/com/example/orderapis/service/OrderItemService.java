package com.example.orderapis.service;

import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.model.orderItem.OrderItemModelForRequest;

import java.util.List;

public interface OrderItemService {
     List<OrderItem> createList(List<OrderItemModelForRequest> items);
}
