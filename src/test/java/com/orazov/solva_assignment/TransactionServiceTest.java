package com.orazov.solva_assignment;


import com.orazov.solva_assignment.dto.TransactionDTO;
import com.orazov.solva_assignment.model.Transaction;
import com.orazov.solva_assignment.repository.TransactionRepository;
import com.orazov.solva_assignment.service.ExchangeRateService;
import com.orazov.solva_assignment.service.LimitService;
import com.orazov.solva_assignment.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private LimitService limitService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_shouldSaveAndReturnTransaction() {

        TransactionDTO inputDto = new TransactionDTO();
        inputDto.setAccountFrom("1234567890");
        inputDto.setAccountTo("0987654321");
        inputDto.setCurrencyShortname("USD");
        inputDto.setSum(BigDecimal.valueOf(100.00));
        inputDto.setExpenseCategory("product");
        inputDto.setDatetime(OffsetDateTime.now());

        Transaction savedTransaction = new Transaction();
        savedTransaction.setId(1L);
        savedTransaction.setAccountFrom(inputDto.getAccountFrom());
        savedTransaction.setAccountTo(inputDto.getAccountTo());
        savedTransaction.setCurrencyShortname(inputDto.getCurrencyShortname());
        savedTransaction.setSum(inputDto.getSum());
        savedTransaction.setExpenseCategory(inputDto.getExpenseCategory());
        savedTransaction.setDatetime(inputDto.getDatetime());
        savedTransaction.setLimitExceeded(false);

        when(exchangeRateService.convertToUSD(any(), any())).thenReturn(BigDecimal.valueOf(100.00));
        when(limitService.isLimitExceeded(any(), any())).thenReturn(false);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        TransactionDTO result = transactionService.createTransaction(inputDto);

        assertEquals(result.getId(), savedTransaction.getId());
        assertEquals(result.getAccountFrom(), savedTransaction.getAccountFrom());
        assertEquals(result.getAccountTo(), savedTransaction.getAccountTo());
        assertEquals(result.getCurrencyShortname(), savedTransaction.getCurrencyShortname());
        assertEquals(result.getSum(), savedTransaction.getSum());
        assertEquals(result.getExpenseCategory(), savedTransaction.getExpenseCategory());
        verify(exchangeRateService).convertToUSD(inputDto.getSum(), inputDto.getCurrencyShortname());
        verify(transactionRepository).save(savedTransaction);
    }
}

