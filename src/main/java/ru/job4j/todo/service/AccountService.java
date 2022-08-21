package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.entity.Account;
import ru.job4j.todo.store.AccountStore;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountStore store;

    public AccountService(AccountStore store) {
        this.store = store;
    }

    public Optional<Account> create(Account account) {
        return store.create(account);
    }

    public Optional<Account> findByLoginAndPas(String login, String password) {
        return store.findByLoginAndPas(login, password);
    }
}
