package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.entity.Account;

import java.util.Optional;
import java.util.function.Function;

@Repository
public class AccountStore {

    private final SessionFactory sf;

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
        });
    }

    public Optional<Account> findByLoginAndPas(final String login, final String password) {
        return this.tx(session -> session.createQuery("from Account a where a.login = :login "
                        + "and a.password = :password", Account.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findFirst()
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
