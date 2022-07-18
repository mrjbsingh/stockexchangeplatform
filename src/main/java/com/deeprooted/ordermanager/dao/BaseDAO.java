package com.deeprooted.ordermanager.dao;

public interface BaseDAO<T> {
    T save(T entity);
    T findById(Long id);
    T update(T entity);
    void delete(Long id);
}
