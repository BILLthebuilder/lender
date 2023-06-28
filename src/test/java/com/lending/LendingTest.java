package com.lending;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lending.dto.ClearOldLoansRequest;
import com.lending.dto.CreateLoanRequest;
import com.lending.dto.RepaymentRequest;
import com.lending.dto.TopupLoanRequest;
import com.lending.repository.LoanRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class LendingTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LoanRepository loanRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    @Order(1)
    public void loanShouldBecreated() throws Exception {
        BigDecimal amount = new BigDecimal(15000.00);
        var createLoanRequest = new CreateLoanRequest(amount,"123456","254722000000","test@email.com");

        mockMvc.perform( post("/api/v1/loans/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createLoanRequest))
        ).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void loanShouldtopup() throws Exception {
        BigDecimal amount = new BigDecimal(15000.00);
        var loan = loanRepository.findSingleRecord();

        var topupLoanRequest = new TopupLoanRequest(amount, loan.getId(),"254722000000");

        mockMvc.perform( put("/api/v1/loans/topup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topupLoanRequest))
        ).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void loanShouldrepay() throws Exception {
        BigDecimal amount = new BigDecimal(150.00);
        var loan = loanRepository.findSingleRecord();
        var repaymentRequest = new RepaymentRequest("",loan.getId(),amount);

        mockMvc.perform( put("/api/v1/loans/repay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(repaymentRequest))
        ).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void loanShouldClearOld() throws Exception {
        var loan = loanRepository.findSingleRecord();
        var clearOldLoansRequest = new ClearOldLoansRequest(null,loan.getId());

        mockMvc.perform( delete("/api/v1/loans/clear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clearOldLoansRequest))
        ).andExpect(status().isOk());
    }
}
