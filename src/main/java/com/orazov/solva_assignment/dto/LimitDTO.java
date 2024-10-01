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
public class LimitDTO {
    private Long id;
    private BigDecimal limitSum;
    private OffsetDateTime limitDatetime;
    private String limitCurrencyShortname;
    private String expenseCategory;
}