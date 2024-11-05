package ru.t1.java.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.java.demo.dto.TransactionDto;
import ru.t1.java.demo.service.TransactionService;

@RequiredArgsConstructor
@RequestMapping("/transaction")
@RestController
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        log.info("Create transaction {}", transactionDto);
        return transactionService.create(transactionDto);
    }

    @GetMapping("/{id}")
    public TransactionDto getTransactionById(@PathVariable Long id) {
        log.info("Get transaction {}", id);
        return transactionService.getById(id);
    }

    @PostMapping("/{id}")
    public TransactionDto updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        log.info("Update transaction {}", id);
        return transactionService.update(id, transactionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Delete transaction {}", id);
        transactionService.deleteById(id);
    }
}
