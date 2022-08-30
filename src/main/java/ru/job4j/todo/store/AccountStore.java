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
public class AccountStore implements TransactionService {

    private final SessionFactory sf;
    public static final String FIND_BY_LOGIN_AND_PAS = "from Account a where a.login = :login "
            + "and a.password = :password";

    public AccountStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<Account> create(final Account account) {
        return this.tx(session -> {
            Integer id = (Integer) session.save(account);
            if (id == null) {

                return Optional.empty();
            }
            return Optional.of(account);
        }, sf);
    }

    public Optional<Account> findByLoginAndPas(final String login, final String password) {
        return this.tx(session -> session.createQuery(FIND_BY_LOGIN_AND_PAS, Account.class)
                        .setParameter("login", login)
                        .setParameter("password", password)
                        .uniqueResultOptional(),
                sf);
    }
}
