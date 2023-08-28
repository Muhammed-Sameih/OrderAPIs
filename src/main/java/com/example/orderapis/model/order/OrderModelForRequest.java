package com.example.orderapis.model.order;

import com.example.orderapis.model.coupon.CouponModelForRequest;
import com.example.orderapis.model.orderItem.OrderItemModelForRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModelForRequest {
    @Valid // This annotation ensures cascading validation for nested objects
    private CouponModelForRequest coupon;

    @NotBlank(message = "Customer email must not be null.")
    private String customerEmail;

    @NotBlank(message = "Customer location must not be null.")
    private String location;

    @NotBlank(message = "Card number must not be blank.")
    @Pattern(regexp = "\\d{16}", message = "Card number must be a 16-digit number.")
    private String cardNumber;

    @Min(value = 0, message = "CVV must be a positive number.")
    @Pattern(regexp = "\\d{3}", message = "CVV must be a 3-digit number.")
    private String cvv;

    @NotNull(message = "Order items must not be null.")
    @Valid
    private List<OrderItemModelForRequest> orderItems;
}
