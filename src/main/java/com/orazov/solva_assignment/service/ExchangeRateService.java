package com.orazov.solva_assignment.service;

import com.orazov.solva_assignment.model.ExchangeRate;
import com.orazov.solva_assignment.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final WebClient.Builder webClientBuilder;

    @Value("${exchange-rate.api.url}")
    private String apiUrl;

    @Value("${exchange-rate.api.key}")
    private String apiKey;

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, WebClient.Builder webClientBuilder) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public BigDecimal convertToUSD(BigDecimal amount, String fromCurrency) {
        if ("USD".equals(fromCurrency)) {
            return amount;
        }

        String currencyPair = fromCurrency + "/USD";
        ExchangeRate rate = exchangeRateRepository.findFirstByCurrencyPairOrderByDateDesc(currencyPair)
                .orElseThrow(() -> new RuntimeException("Exchange rate not found for " + currencyPair));

        return amount.divide(rate.getRate(), 2, RoundingMode.HALF_UP);
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateExchangeRates() {
        updateRate("KZT/USD");
        updateRate("RUB/USD");
    }

    private void updateRate(String currencyPair) {
        ExchangeRate latestRate = webClientBuilder.build()
                .get()
                .uri(apiUrl + "/latest?base=" + currencyPair.split("/")[0] + "&symbols=" + currencyPair.split("/")[1] + "&apikey=" + apiKey)
                .retrieve()
                .bodyToMono(ExchangeRate.class)
                .block();

        if (latestRate != null) {
            latestRate.setDate(LocalDate.now());
            exchangeRateRepository.save(latestRate);
        }
    }
}

