package org.api.service;

import org.api.dto.AccountDTO;
import org.api.dto.TransactionDTO;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<AccountDTO> getAccountsByUserId(Long userId);
    List<TransactionDTO> getTransactionsByAccountNumber(String accountNumber);
    Optional<AccountDTO> findByAccountNumber(String accountNumber);
}
