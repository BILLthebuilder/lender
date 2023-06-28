package com.lending.controller;


import com.lending.Service.LoanService;
import com.lending.dto.*;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping("request")
    public ResponseEntity<GenericResponse> createLoan(@RequestBody @Valid CreateLoanRequest request, Errors errors){
        GenericResponse response;

        if (errors.hasFieldErrors()) {
            FieldError fieldError = errors.getFieldError();
            response = new GenericResponse(fieldError.getDefaultMessage(), "FAILED");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        try {
            if(loanService.createLoanRequest(request)){
                response = new GenericResponse("Loan created successfully","SUCCESS");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }else {
                response = new GenericResponse("That loan already exists", "FAILED");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            response = new GenericResponse(ex.getMessage(), "FAILED");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
