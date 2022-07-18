package com.deeprooted.ordermanager.dao;

public interface TransactionBaseDAO<T> {
    T save(T entity);
    T findById(Long id);
}
