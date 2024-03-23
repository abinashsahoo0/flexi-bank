package com.jtbank.backend.service;

import com.jtbank.backend.model.Transaction;

public interface ITransactionService {
    void addTransaction(Transaction transaction, long accountNumber);
}
