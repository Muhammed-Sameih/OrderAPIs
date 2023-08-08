package com.example.orderapis.model.order;

import com.example.orderapis.model.coupon.CouponRequestDTO;
import com.example.orderapis.model.customer.CustomerDetailedDTO;
import com.example.orderapis.model.orderItem.OrderItemResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime shipmentDate;

    private BigDecimal totalPrice;


    private List<OrderItemResponseDTO> orderItems;

    private CouponRequestDTO couponRequestDTO;

    private CustomerDetailedDTO customerDTO;

}
