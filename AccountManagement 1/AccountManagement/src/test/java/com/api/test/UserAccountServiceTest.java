package com.api.test;

import org.api.dto.AccountDTO;
import org.api.dto.TransactionDTO;
import org.api.dto.UserDTO;
import org.api.model.Account;
import org.api.model.Transaction;
import org.api.model.User;
import org.api.repository.AccountRepository;
import org.api.repository.AccountTransactionRepository;
import org.api.repository.UserRepository;
import org.api.service.impl.AccountService;
import org.api.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountTransactionRepository transactionRepository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private AccountService accountService;

    private User user;
    private Account account;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        account = new Account();
        account.setId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountName("Test Account");
        account.setAccountType("Savings");
        account.setBalanceDate(LocalDate.now());
        account.setCurrency("USD");
        account.setOpeningAvailableBalance(1000.0);
        account.setUser(user);

        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccount(account);
        transaction.setAccountNumber("1234567890");
        transaction.setAccountName("Test Account");
        transaction.setValueDate(LocalDate.now());
        transaction.setCurrency("USD");
        transaction.setDebitAmount(500.0);
        transaction.setCreditAmount(0.0);
        transaction.setDebitOrCredit("Debit");
        transaction.setTransactionNarrative("Test Transaction");
    }

    @Test
    void testGetUserByUserId() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserDTO> result = userService.getUserbyUserId(1L);

        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAccountsByUserId() {
        when(accountRepository.findByUserId(1L)).thenReturn(Arrays.asList(account));

        List<AccountDTO> result = accountService.getAccountsByUserId(1L);

        assertFalse(result.isEmpty());
        assertEquals("1234567890", result.get(0).getAccountNumber());
        verify(accountRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetTransactionsByAccountNumber() {
        when(transactionRepository.findByAccount_AccountNumber("1234567890"))
                .thenReturn(Optional.of(Arrays.asList(transaction)));

        List<TransactionDTO> result = accountService.getTransactionsByAccountNumber("1234567890");

        assertFalse(result.isEmpty());
        assertEquals("Test Transaction", result.get(0).getTransactionNarrative());
        verify(transactionRepository, times(1)).findByAccount_AccountNumber("1234567890");
    }

    @Test
    void testFindByAccountNumber() {
        when(accountRepository.findByAccountNumber("1234567890"))
                .thenReturn(Optional.of(account));

        Optional<AccountDTO> result = accountService.findByAccountNumber("1234567890");

        assertTrue(result.isPresent());
        assertEquals("1234567890", result.get().getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber("1234567890");
    }
}