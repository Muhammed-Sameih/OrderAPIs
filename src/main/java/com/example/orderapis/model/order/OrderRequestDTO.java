package com.example.orderapis.model.order;


import com.example.orderapis.model.coupon.CouponRequestDTO;
import com.example.orderapis.model.customer.CustomerDetailedDTO;
import com.example.orderapis.model.orderItem.OrderItemRequestDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "Order items must not be null.")
    private List<OrderItemRequestDTO> orderItems;

    @NotNull(message = "Coupon request must not be null.")
    private CouponRequestDTO couponRequestDTO;

    @NotNull(message = "Customer must not be null.")
    private CustomerDetailedDTO customerDetailedDTO;

    @NotBlank(message = "Card number must not be blank.")
    @Pattern(regexp = "\\d{16}", message = "Card number must be a 16-digit number.")
    private String cardNumber;

    @Min(value = 0, message = "CVV must be a positive number.")
    @Pattern(regexp = "\\d{3}", message = "CVV must be a 3-digit number.")
    private String cvv;
}
