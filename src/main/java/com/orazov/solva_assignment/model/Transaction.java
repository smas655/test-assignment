package com.orazov.solva_assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from", nullable = false)
    private String accountFrom;

    @Column(name = "account_to", nullable = false)
    private String accountTo;

    @Column(name = "currency_shortname", nullable = false)
    private String currencyShortname;

    @Column(nullable = false)
    private BigDecimal sum;

    @Column(name = "expense_category", nullable = false)
    private String expenseCategory;

    @Column(nullable = false)
    private OffsetDateTime datetime;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;
}