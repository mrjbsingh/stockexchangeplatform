package com.deeprooted.ordermanager.dao;

import com.deeprooted.ordermanager.filehandler.TransactionOutputWriter;
import com.deeprooted.ordermanager.models.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TransactionDAO implements TransactionBaseDAO<Transaction>{

    private static Map<Long, Transaction> transactionMap;
    private TransactionOutputWriter transactionOutputWriter;

    public TransactionDAO(){
        transactionMap = new HashMap<>();
        transactionOutputWriter = new TransactionOutputWriter();
    }
    @Override
    public Transaction save(Transaction transaction) {
        //writing transaction details into output file
        transactionOutputWriter.writeToOutputFile(transaction);
        return transactionMap.putIfAbsent(transaction.getId(), transaction);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionMap.get(id);
    }

    public static Map<Long, Transaction> getAll(){
        return transactionMap;
    }

    private void validateTransaction(Long id) {
        Optional.ofNullable(transactionMap.get(id))
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
    public int size(){
        return transactionMap.size();
    }
}
