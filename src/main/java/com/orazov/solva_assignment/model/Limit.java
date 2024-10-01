package com.orazov.solva_assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "limits")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_sum", nullable = false)
    private BigDecimal limitSum;

    @Column(name = "limit_datetime", nullable = false)
    private OffsetDateTime limitDatetime;

    @Column(name = "limit_currency_shortname", nullable = false)
    private String limitCurrencyShortname;

    @Column(name = "expense_category", nullable = false)
    private String expenseCategory;

}
