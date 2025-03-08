package org.api.dto;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private String accountNumber;
    private String accountName;
    private LocalDate valueDate;
    private String currency;
    private double debitAmount;
    private double creditAmount;
    private String debitOrCredit;
    private String transactionNarrative;

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

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDebitOrCredit() {
        return debitOrCredit;
    }

    public void setDebitOrCredit(String debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }

    public String getTransactionNarrative() {
        return transactionNarrative;
    }

    public void setTransactionNarrative(String transactionNarrative) {
        this.transactionNarrative = transactionNarrative;
    }

    public TransactionDTO(Long id, String accountNumber, String accountName, LocalDate valueDate, String currency, double debitAmount, double creditAmount, String debitOrCredit, String transactionNarrative) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.valueDate = valueDate;
        this.currency = currency;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.debitOrCredit = debitOrCredit;
        this.transactionNarrative = transactionNarrative;
    }
}

