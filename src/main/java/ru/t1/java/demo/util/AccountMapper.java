package ru.t1.java.demo.util;

import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.model.Account;

public class AccountMapper {
    public static Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .accountType(accountDto.getAccountType())
                .balance(accountDto.getBalance())
                .clientId(accountDto.getClientId())
                .build();
    }

    public static AccountDto toDto(Account account) {
        return AccountDto.builder()
                .accountType(account.getAccountType())
                .id(account.getId())
                .balance(account.getBalance())
                .clientId(account.getClientId())
                .build();
    }
}
