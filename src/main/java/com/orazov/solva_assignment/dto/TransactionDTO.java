package com.orazov.solva_assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    private String expenseCategory;
    private OffsetDateTime datetime;
    private Boolean limitExceeded;

    }
