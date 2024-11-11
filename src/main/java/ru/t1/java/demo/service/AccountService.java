package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto account);

    List<AccountDto> createAccounts(List<AccountDto> accountDtos);

    AccountDto getById(Long id);

    AccountDto update(Long id, AccountDto account);

    void deleteById(Long id);
}
