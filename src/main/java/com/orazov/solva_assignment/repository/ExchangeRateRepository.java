package com.orazov.solva_assignment.repository;

import com.orazov.solva_assignment.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findFirstByCurrencyPairOrderByDateDesc(String currencyPair);
    Optional<ExchangeRate> findByCurrencyPairAndDate(String currencyPair, LocalDate date);
}
