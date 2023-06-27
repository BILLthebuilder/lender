package com.lending.Repository;

import com.lending.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {

    Optional<Loan> findById(UUID id);

    Optional<Loan> findLoanByEmail(String email);

    Optional<Loan> findLoanByPhoneNumber(String phoneNumber);

    Optional<Loan> findLoanByLoanCreationDate(Date date);

    Optional<Loan> findLoanByLoanTopupDate(Date date);
}
