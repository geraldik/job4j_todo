package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.entity.Account;
import ru.job4j.todo.utility.TransactionService;

import java.util.Optional;
import java.util.function.Function;

@Repository
public class AccountStore {

    private final SessionFactory sf;

    private final TransactionService transactionService;

    public AccountStore(SessionFactory sf) {
        this.sf = sf;
        transactionService = new TransactionService() {
            @Override
            public <T> T tx(Function<Session, T> command, SessionFactory sf) {
                return TransactionService.super.tx(command, sf);
            }
        };
    }

    public Optional<Account> create(final Account account) {
        return transactionService.tx(session -> {
            Integer id = (Integer) session.save(account);
            if (id == null) {
                return Optional.empty();
            }
            return Optional.of(account);
        }, sf);
    }

    public Optional<Account> findByLoginAndPas(final String login, final String password) {
        return transactionService.tx(session -> session.createQuery("from Account a where a.login = :login "
                                + "and a.password = :password", Account.class)
                        .setParameter("login", login)
                        .setParameter("password", password)
                        .uniqueResultOptional(),
                sf);
    }
}
