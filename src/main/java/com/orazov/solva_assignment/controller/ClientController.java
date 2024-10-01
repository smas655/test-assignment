package com.orazov.solva_assignment.controller;

import com.orazov.solva_assignment.dto.LimitDTO;
import com.orazov.solva_assignment.dto.TransactionDTO;
import com.orazov.solva_assignment.service.LimitService;
import com.orazov.solva_assignment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final TransactionService transactionService;
    private final LimitService limitService;

    @Autowired
    public ClientController(TransactionService transactionService, LimitService limitService) {
        this.transactionService = transactionService;
        this.limitService = limitService;
    }

    @GetMapping("/transactions/exceeded")
    public ResponseEntity<List<TransactionDTO>> getExceededTransactions() {
        List<TransactionDTO> exceededTransactions = transactionService.getExceededTransactions();
        return ResponseEntity.ok(exceededTransactions);
    }

    @PostMapping("/limits")
    public ResponseEntity<LimitDTO> setNewLimit(@RequestBody LimitDTO limitDTO) {
        LimitDTO newLimit = limitService.setNewLimit(limitDTO);
        return ResponseEntity.ok(newLimit);
    }

    @GetMapping("/limits")
    public ResponseEntity<List<LimitDTO>> getAllLimits() {
        List<LimitDTO> limits = limitService.getAllLimits();
        return ResponseEntity.ok(limits);
    }
}
