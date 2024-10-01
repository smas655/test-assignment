package com.orazov.solva_assignment.service;

import com.orazov.solva_assignment.dto.LimitDTO;
import com.orazov.solva_assignment.model.Limit;
import com.orazov.solva_assignment.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LimitService {

    private final LimitRepository limitRepository;

    @Autowired
    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public boolean isLimitExceeded(BigDecimal amountInUSD, String expenseCategory) {
        Limit currentLimit = limitRepository.findFirstByExpenseCategoryOrderByLimitDatetimeDesc(expenseCategory)
                .orElseThrow(() -> new RuntimeException("No limit found for category: " + expenseCategory));

        return amountInUSD.compareTo(currentLimit.getLimitSum()) > 0;
    }

    public LimitDTO setNewLimit(LimitDTO limitDTO) {
        Limit limit = convertToEntity(limitDTO);
        limit.setLimitDatetime(OffsetDateTime.now());
        Limit savedLimit = limitRepository.save(limit);
        return convertToDTO(savedLimit);
    }

    public List<LimitDTO> getAllLimits() {
        List<Limit> limits = limitRepository.findAll();
        return limits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Limit convertToEntity(LimitDTO dto) {
        Limit limit = new Limit();
        limit.setLimitSum(dto.getLimitSum());
        limit.setLimitCurrencyShortname(dto.getLimitCurrencyShortname());
        limit.setExpenseCategory(dto.getExpenseCategory());
        return limit;
    }

    private LimitDTO convertToDTO(Limit entity) {
        LimitDTO dto = new LimitDTO();
        dto.setId(entity.getId());
        dto.setLimitSum(entity.getLimitSum());
        dto.setLimitDatetime(entity.getLimitDatetime());
        dto.setLimitCurrencyShortname(entity.getLimitCurrencyShortname());
        dto.setExpenseCategory(entity.getExpenseCategory());
        return dto;
    }
}