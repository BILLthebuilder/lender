package com.lending.dto;

import java.util.Date;
import java.util.UUID;

public record ClearOldLoansRequest(
        Date date, UUID id
) {
}
