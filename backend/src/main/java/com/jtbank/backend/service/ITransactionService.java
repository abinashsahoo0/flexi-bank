package com.jtbank.backend.service;

import com.jtbank.backend.model.Transaction;

import java.util.List;

public interface ITransactionService {
    void addTransaction(Transaction transaction, long accountNumber);

    List<Transaction> getDebitedTransactions(long accountNumber);

    List<Transaction> getCreditedTransactions(long accountNumber, int pageSize, int pageNumber);

    List<Transaction> getTransferedTransactions(long accountNumber);
}
