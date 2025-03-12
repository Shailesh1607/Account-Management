package org.api.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.api.dto.AccountDTO;
import org.api.dto.TransactionDTO;
import org.api.exception.NoDataFoundException;
import org.api.model.Account;
import org.api.model.Transaction;
import org.api.repository.AccountRepository;
import org.api.repository.AccountTransactionRepository;
import org.api.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final AccountTransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, AccountTransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<AccountDTO> getAccountsByUserId(Long userId) {
        log.info("Getting Accounts details...");
        List<AccountDTO> accountDTOs = accountRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (accountDTOs.isEmpty()) {
            log.info("No accounts found");
            throw new NoDataFoundException("No accounts found");
        }
        log.info("Fetched Accounts successfully...");
        return accountDTOs;
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccountNumber(String accountNumber) {
        log.info("Fetching transaction by account number: "+maskAccountNumber(accountNumber));
        return transactionRepository.findByAccount_AccountNumber(accountNumber)
                .orElseThrow(() -> new NoDataFoundException("No Transaction Found"))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).map(this::convertToDTO);
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAccount().getAccountNumber(),
                transaction.getAccount().getAccountName(),
                transaction.getValueDate(),
                transaction.getCurrency(),
                transaction.getDebitAmount(),
                transaction.getCreditAmount(),
                transaction.getDebitOrCredit(),
                transaction.getTransactionNarrative()
        );
    }


    private AccountDTO convertToDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountName(), // Renamed from accountHolderName to match CamelCase
                account.getAccountType(),
                account.getBalanceDate(),
                account.getCurrency(),
                account.getOpeningAvailableBalance()
        );
    }

    private String maskAccountNumber(String accountNumber) {
        // Keeping last 4 digits visible, rest masked
        if (accountNumber.length() > 4) {
            return "****" + accountNumber.substring(accountNumber.length() - 4);
        }
        return accountNumber; // If somehow shorter than 4, return as-is
    }

}
