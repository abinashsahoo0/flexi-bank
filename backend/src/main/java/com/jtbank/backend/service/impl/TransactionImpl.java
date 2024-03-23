package com.jtbank.backend.service.impl;

import com.jtbank.backend.model.Transaction;
import com.jtbank.backend.repository.AccountRepository;
import com.jtbank.backend.repository.TransactionRepository;
import com.jtbank.backend.service.IAccountService;
import com.jtbank.backend.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TransactionImpl implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Async
    @Override
    public void addTransaction(Transaction transaction, long accountNumber) {
        var account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        var transactions = account.getTransactions();

        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        transaction.setTimestamp(LocalDateTime.now());
        transactions.add(transaction);

        transactionRepository.save(transaction);
    }
}
