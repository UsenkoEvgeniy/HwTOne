package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto create(TransactionDto transactionDto);

    TransactionDto getById(Long id);

    TransactionDto update(Long id, TransactionDto transactionDto);

    void deleteById(Long id);

    List<TransactionDto> createTransactions(List<TransactionDto> transactionList);
}
