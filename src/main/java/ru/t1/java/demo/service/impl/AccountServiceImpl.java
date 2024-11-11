package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.exception.NotFoundException;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.repository.AccountRepository;
import ru.t1.java.demo.service.AccountService;
import ru.t1.java.demo.util.AccountMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        return AccountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public List<AccountDto> createAccounts(List<AccountDto> accountDtos) {
        List<Account> accounts = accountDtos.stream().map(AccountMapper::toEntity).toList();
        return accountRepository.saveAll(accounts).stream().map(AccountMapper::toDto).toList();
    }

    @Override
    public AccountDto getById(Long id) {
        return AccountMapper.toDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found account for id=" + id)));
    }

    @Override
    public AccountDto update(Long id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found account for id=" + id));
        if (accountDto.getAccountType() != null) {
            account.setAccountType(accountDto.getAccountType());
        }
        if (accountDto.getBalance() != null) {
            account.setBalance(accountDto.getBalance());
        }
        if (accountDto.getClientId() != null) {
            account.setClientId(accountDto.getClientId());
        }
        return AccountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
