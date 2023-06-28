package com.lending.Service;

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
            created = true;
        } catch (Exception e) {
            log.error("Error creating loan=%s", e);
        }

        return created;
    }

    @Transactional
    public boolean topupLoan(BigDecimal topupAmount, UUID id) {
        boolean status = false;
        var loan = loanRepository.findById(id);
        BigDecimal currentAmount = loan.getAmount();
        try {
            if (loanExists(id)) {
                loan.setAmount(currentAmount.add(topupAmount));
                loanRepository.save(loan);
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
    public boolean repayLoan(String repaymentStatus, UUID id) {
        boolean status = false;
        var loan = loanRepository.findById(id);
        try {
            if (loanExists(id)) {
                loan.setRepaymentStatus(repaymentStatus);
                loanRepository.save(loan);
                status = true;
            } else {
                log.error("Get a loan first to qualify for a repay");
            }
        } catch (Exception e) {
            log.error("Error repaying loan=%s", e);
        }

        return status;
    }

    @Transactional
    public boolean clearOldLoans(Date date, UUID id) {
        boolean status = false;
        var loan = loanRepository.findById(id);
        var getCurrentLoanDate = loan.getDateCreated();
        var calendar = Calendar.getInstance();
        calendar.setTime(getCurrentLoanDate);
        calendar.add(Calendar.MONTH, 6);

        var sixMonthsLater = calendar.getTime();

        // Check repayment status(-1 is for defaulted loans)
        var repaymentStatus = loan.getRepaymentStatus();

        try {
            if (loanExists(id)) {
                //Check if loan is 6 months old or past 6 months
                boolean isPastOrEqual = getCurrentLoanDate.before(sixMonthsLater) || getCurrentLoanDate.equals(sixMonthsLater);

                if (isPastOrEqual || repaymentStatus.equalsIgnoreCase("-1")) {
                    loanRepository.delete(loan);
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
