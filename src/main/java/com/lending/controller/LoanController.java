package com.lending.controller;


import com.lending.service.LoanService;
import com.lending.dto.*;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping("request")
    public ResponseEntity<GenericResponse> createLoan(@RequestBody @Valid CreateLoanRequest request, Errors errors){
     return loanService.createLoanRequest(request,errors);
    }

    @PutMapping("topup")
    public ResponseEntity<GenericResponse> topupLoan(@RequestBody @Valid TopupLoanRequest request, Errors errors){
       return loanService.topupLoan(request,errors);
    }

    @PutMapping("repay")
    public ResponseEntity<GenericResponse> repayLoan(@RequestBody @Valid RepaymentRequest request, Errors errors){
        return loanService.repayLoan(request,errors);
    }

    @DeleteMapping("clear")
    public ResponseEntity<GenericResponse> clearLoan(@RequestBody @Valid ClearOldLoansRequest request, Errors errors){
       return loanService.clearOldLoans(request,errors);
    }

}
