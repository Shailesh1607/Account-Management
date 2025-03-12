package org.api.repository;


import org.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountTransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findByAccount_AccountNumber(String accountNumber);
}

