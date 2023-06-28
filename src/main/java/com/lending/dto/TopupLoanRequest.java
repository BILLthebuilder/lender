package com.lending.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TopupLoanRequest(
        BigDecimal topupAmount,
        UUID id
) {
}
