package com.orazov.solva_assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_pair", nullable = false)
    private String currencyPair;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @Column(name = "date", nullable = false)
    private LocalDate date;

}
