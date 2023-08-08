package com.example.orderapis.model.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

    private String cardNumber;
    private String cvv;
    private BigDecimal amount;

}
