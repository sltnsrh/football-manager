package com.footballmanager.service;

public interface TransactionService<T> {
    void doTransaction(T t);
}
