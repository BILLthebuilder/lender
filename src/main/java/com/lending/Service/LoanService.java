package com.lending.Service;

import com.lending.Util.SendSms;
import com.lending.dto.ClearOldLoansRequest;
import com.lending.dto.RepaymentRequest;
import com.lending.dto.TopupLoanRequest;
import com.lending.repository.LoanRepository;
import com.lending.dto.CreateLoanRequest;
import com.lending.model.Loan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final LoanRepository loanRepository;

    @Transactional
    public boolean createLoanRequest(CreateLoanRequest request) {
        boolean created = false;

        var loan = new Loan();

        try {
            loan.setAmount(request.amount());
            loan.setIdNumber(request.idNumber());
            loan.setPhoneNumber(request.phoneNumber());
            loan.setEmail(request.email());
            loanRepository.save(loan);
            //SendSms.sendSms(request.phoneNumber(),"Loan request processed succesfully");
            created = true;
        } catch (Exception e) {
            log.error("Error creating loan=%s", e);
        }

        return created;
    }

    @Transactional
    public boolean topupLoan(TopupLoanRequest request) {
        boolean status = false;
        var loan = loanRepository.findById(request.id());
        BigDecimal currentAmount = loan.getAmount();
        try {
            if (loanExists(request.id())) {
                loan.setAmount(currentAmount.add(request.topupAmount()));
                loanRepository.save(loan);
                //SendSms.sendSms(request.phoneNumber(),"Loan topup request processed succesfully");
                status = true;
            } else {
                log.error("Get a loan first to qualify for a topup");
            }
        } catch (Exception e) {
            log.error("Error toping up loan=%s", e);
        }
        return status;
    }

    @Transactional
    public boolean repayLoan(RepaymentRequest request) {
        boolean status = false;
        var loan = loanRepository.findById(request.id());
        var repaymentAmont = request.amount();
        var currentLoanAmount = loan.getAmount();
        var remainingAmount = currentLoanAmount.subtract(repaymentAmont);
        var repayMentStatus = "";
        if(remainingAmount.compareTo(BigDecimal.ZERO)>0){
           repayMentStatus = "1"; //Fully repaid
        }else {
            repayMentStatus = "2"; // partially repaid
        }
        try {
            if (loanExists(request.id())) {
                loan.setAmount(currentLoanAmount.subtract(repaymentAmont));
                loan.setRepaymentStatus(repayMentStatus);
                loanRepository.save(loan);
                status = true;
            } else {
                log.error("Get a loan first to allow repayment");
            }
        } catch (Exception e) {
            log.error("Error repaying loan=%s", e);
        }

        return status;
    }

    @Transactional
    public boolean clearOldLoans(ClearOldLoansRequest request) {
        boolean status = false;
        var loan = loanRepository.findById(request.id());
        var getCurrentLoanDate = loan.getDateCreated();
        var calendar = Calendar.getInstance();
        calendar.setTime(getCurrentLoanDate);
        calendar.add(Calendar.MONTH, 6);

        var sixMonthsLater = calendar.getTime();

        // Check repayment status(-1 is for defaulted loans)
        var repaymentStatus = loan.getRepaymentStatus();

        try {
            if (loanExists(request.id())) {
                //Check if loan is 6 months old or past 6 months
                boolean isPastOrEqual = getCurrentLoanDate.before(sixMonthsLater) || getCurrentLoanDate.equals(sixMonthsLater);

                if (isPastOrEqual || repaymentStatus.equalsIgnoreCase("-1")) {
                    loanRepository.delete(loan);
                    status = true;
                }
            }
        } catch (Exception e) {
            log.error("Error clearing loan=%s", e);
        }
        return status;
    }

    public boolean loanExists(UUID id) {
        return loanRepository.findById(id) != null;
    }
}
