package com.lending.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record RepaymentRequest(
        String repaymentStatus, UUID id, BigDecimal amount
) {
}
