package com.lending.dto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateLoanRequest(

        BigDecimal amount,

        @NotEmpty(message = "idNumber cannot be blank")
        String idNumber,
        @NotEmpty(message = "phoneNumber cannot be blank")
        String phoneNumber,

        @NotEmpty(message = "email cannot be blank")
        @Email(message = "Invalid Email Address")
        String email
) {
}
