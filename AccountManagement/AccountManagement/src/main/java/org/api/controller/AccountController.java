package org.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.api.dto.AccountDTO;
import org.api.dto.TransactionDTO;
import org.api.response.ApiResponse;
import org.api.service.impl.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
@Api(value = "Accounts", description = "Operations related to accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get all accounts by user id", notes = "Retrieve a list of all accounts without transactions")
    public ResponseEntity<ApiResponse<List<AccountDTO>>> getAccountsByUserId(@PathVariable Long userId) {
        List<AccountDTO> accounts = accountService.getAccountsByUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Accounts fetched successfully", accounts));
    }

    @GetMapping("/{accountNumber}/transactions")
    @ApiOperation(value = "Get account transactions", notes = "Retrieve transactions for a specific account")
    public ResponseEntity<ApiResponse<List<TransactionDTO>>> getTransactionsByAccount(@PathVariable String accountNumber) {
        Optional<AccountDTO> account = accountService.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            List<TransactionDTO> transactions = accountService.getTransactionsByAccountNumber(accountNumber);
            return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Transactions fetched successfully", transactions));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("ERROR", "Account not found"));
        }
    }
}
