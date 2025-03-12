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
public class AccountDTO {

    private Long id;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private LocalDate balanceDate;
    private String currency;
    private double openingAvailableBalance;

}
