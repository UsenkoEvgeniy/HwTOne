package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto account);

    AccountDto getById(Long id);

    AccountDto update(Long id, AccountDto account);

    void deleteById(Long id);
}
