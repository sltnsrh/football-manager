package com.footballmanager.service;

import com.footballmanager.dto.request.TransactionRequestDto;

public interface TransactionService {
    void doPlayerTransaction(TransactionRequestDto transactionRequestDto);
}
