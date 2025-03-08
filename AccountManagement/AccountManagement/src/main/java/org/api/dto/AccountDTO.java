package org.api.dto;

import java.time.LocalDate;

public class AccountDTO {

    private Long id;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private LocalDate balanceDate;
    private String currency;
    private double openingAvailableBalance;

    // Constructors
    public AccountDTO() {}

    public AccountDTO(Long id, String accountNumber, String accountName, String accountType,
                      LocalDate balanceDate, String currency, double openingAvailableBalance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balanceDate = balanceDate;
        this.currency = currency;
        this.openingAvailableBalance = openingAvailableBalance;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDate getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(LocalDate balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getOpeningAvailableBalance() {
        return openingAvailableBalance;
    }

    public void setOpeningAvailableBalance(double openingAvailableBalance) {
        this.openingAvailableBalance = openingAvailableBalance;
    }
}
