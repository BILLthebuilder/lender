package com.lending.mappers;

import com.lending.dto.CreateLoanRequest;
import com.lending.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")

public interface LoanMapper {
    //LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "loanCreationDate", ignore = true)
    @Mapping(target = "loanTopupDate", ignore = true)
    @Mapping(target = "loanStatus", ignore = true)
    @Mapping(target = "repaymentStatus", ignore = true)
    Loan toLoan(CreateLoanRequest request);
}

