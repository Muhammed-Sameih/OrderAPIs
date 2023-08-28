package com.example.orderapis.service.impl;

import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.mapper.OrderItemMapper;
import com.example.orderapis.model.orderItem.OrderItemModelForRequest;
import com.example.orderapis.repo.OrderItemRepo;
import com.example.orderapis.service.OrderItemService;
import com.example.orderapis.util.CodeGenerator;
import com.example.orderapis.util.RestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private RestApiClient restApiClient;
    @Override
    public List<OrderItem> createList(List<OrderItemModelForRequest> items) {
        List<OrderItem> orderItems = items.stream()
                .map(orderItemMapper::toEntity)
                .map(item -> {
                    item.setCode(CodeGenerator.generateOrderItemCode());
                    item.setPrice(restApiClient.getProductPriceByCode(item.getProductCode()).getPrice());
                    return item;
                })
                .collect(Collectors.toList());
        return orderItemRepo.saveAll(orderItems);
    }
}
