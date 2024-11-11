package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.exception.NotFoundException;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.dto.TransactionDto;
import ru.t1.java.demo.repository.TransactionRepository;
import ru.t1.java.demo.service.TransactionService;
import ru.t1.java.demo.util.TransactionMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.toEntity(transactionDto);
        return TransactionMapper.toDto(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDto getById(Long id) {
        return TransactionMapper.toDto(transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found transaction for id=" + id)));
    }

    @Override
    public TransactionDto update(Long id, TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found transaction for id=" + id));
        if (transactionDto.getAccountId() != null) {
            transaction.setAccountId(transactionDto.getAccountId());
        }
        if (transactionDto.getAmount() != null) {
            transaction.setAmount(transactionDto.getAmount());
        }
        if (transactionDto.getTransactionDate() != null) {
            transaction.setTransactionDate(transactionDto.getTransactionDate());
        }
        return TransactionMapper.toDto(transactionRepository.save(transaction));
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionDto> createTransactions(List<TransactionDto> transactionList) {
        List<Transaction> accounts = transactionList.stream().map(TransactionMapper::toEntity).toList();
        return transactionRepository.saveAll(accounts).stream().map(TransactionMapper::toDto).toList();
    }
}
