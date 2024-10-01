package com.orazov.solva_assignment.service;

import com.orazov.solva_assignment.dto.TransactionDTO;
import com.orazov.solva_assignment.model.Transaction;
import com.orazov.solva_assignment.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ExchangeRateService exchangeRateService;
    private final LimitService limitService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              ExchangeRateService exchangeRateService,
                              LimitService limitService) {
        this.transactionRepository = transactionRepository;
        this.exchangeRateService = exchangeRateService;
        this.limitService = limitService;
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = convertToEntity(transactionDTO);

        BigDecimal amountInUSD = exchangeRateService.convertToUSD(transaction.getSum(), transaction.getCurrencyShortname());

        boolean limitExceeded = limitService.isLimitExceeded(amountInUSD, transaction.getExpenseCategory());
        transaction.setLimitExceeded(limitExceeded);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }

    public List<TransactionDTO> getExceededTransactions() {
        List<Transaction> exceededTransactions = transactionRepository.findByLimitExceededTrue();
        return exceededTransactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Transaction convertToEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setAccountFrom(dto.getAccountFrom());
        transaction.setAccountTo(dto.getAccountTo());
        transaction.setCurrencyShortname(dto.getCurrencyShortname());
        transaction.setSum(dto.getSum());
        transaction.setExpenseCategory(dto.getExpenseCategory());
        transaction.setDatetime(dto.getDatetime());
        return transaction;
    }

    private TransactionDTO convertToDTO(Transaction entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(entity.getId());
        dto.setAccountFrom(entity.getAccountFrom());
        dto.setAccountTo(entity.getAccountTo());
        dto.setCurrencyShortname(entity.getCurrencyShortname());
        dto.setSum(entity.getSum());
        dto.setExpenseCategory(entity.getExpenseCategory());
        dto.setDatetime(entity.getDatetime());
        dto.setLimitExceeded(entity.getLimitExceeded());
        return dto;
    }
}
