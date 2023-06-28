package com.lending.repository;

import com.lending.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {

   Loan findById(UUID id);

  Loan findLoanByPhoneNumber(String phoneNumber);

    Optional<Loan> findLoanByLoanCreationDate(Date date);

    Optional<Loan> findLoanByLoanTopupDate(Date date);
}
