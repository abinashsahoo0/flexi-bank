package com.jtbank.backend.controller;

import com.jtbank.backend.dto.TransactionDTO;
import com.jtbank.backend.mapper.AccountMapper;
import com.jtbank.backend.mapper.TransactionMapper;
import com.jtbank.backend.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final ITransactionService service;

    @GetMapping("/{accountNumber}/credit")
    public List<TransactionDTO> creditedTransactions(@PathVariable long accountNumber,
                                                     @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        var results = service.getCreditedTransactions(accountNumber, pageNumber, pageSize);
        return results.stream().map(TransactionMapper::dtoMapper).toList();
    }

    @GetMapping("/{accountNumber}/debit")
    public List<TransactionDTO> debitedTransactions(@PathVariable long accountNumber) {
        var results = service.getDebitedTransactions(accountNumber);
        return results.stream().map(TransactionMapper::dtoMapper).toList();
    }

    @GetMapping("/{accountNumber}/transfer")
    public List<TransactionDTO> transferedTransactions(@PathVariable long accountNumber) {
        var results = service.getTransferedTransactions(accountNumber);
        return results.stream().map(TransactionMapper::dtoMapper).toList();
    }
}
