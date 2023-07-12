package com.lending.mappers;

import com.lending.dto.CreateLoanRequest;
import com.lending.model.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface LoanMapper {
Loan toLoan(CreateLoanRequest request);
}

