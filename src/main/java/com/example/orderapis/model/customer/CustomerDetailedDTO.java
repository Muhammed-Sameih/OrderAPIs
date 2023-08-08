package com.example.orderapis.model.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailedDTO {

    @Email(message = "Customer email is not valid!")
    @NotBlank(message = "Customer email is required!")
    private String email;


    @NotBlank(message = "Customer location is required!")
    private String location;
}