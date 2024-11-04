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
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.service.AccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        log.info("Create account {}", accountDto);
        return accountService.createAccount(accountDto);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        log.info("Get account {}", id);
        return accountService.getById(id);
    }

    @PostMapping("/{id}")
    public AccountDto updateAccount(@PathVariable Long id, @RequestBody AccountDto account) {
        log.info("Update accountId {}", id);
        return accountService.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        log.info("Delete account {}", id);
        accountService.deleteById(id);
    }
}
