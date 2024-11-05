package ru.t1.java.demo.util;

import ru.t1.java.demo.dto.TransactionDto;
import ru.t1.java.demo.model.Transaction;

public class TransactionMapper {

    public static Transaction toEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .accountId(transactionDto.getAccountId())
                .amount(transactionDto.getAmount())
                .transactionDate(transactionDto.getTransactionDate())
                .build();
    }

    public static TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
