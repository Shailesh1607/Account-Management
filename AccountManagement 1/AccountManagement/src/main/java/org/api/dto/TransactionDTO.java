package org.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}

