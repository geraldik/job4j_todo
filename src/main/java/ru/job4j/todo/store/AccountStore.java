package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.entity.Account;
import ru.job4j.todo.entity.Item;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountStore {

    private final SessionFactory sf;

    public AccountStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<Account> create(Account account) {
        Session session = sf.openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(account);
        if (id == null) {
            return Optional.empty();
        }
        session.getTransaction().commit();
        session.close();
        return Optional.of(account);
    }

    public Optional<Account> findByLoginAndPas(String login, String password) {
        Session session = sf.openSession();
        session.beginTransaction();
        Optional<Account> result = session.createQuery("from Account a where a.login = :login "
                        + "and a.password = :password", Account.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findFirst();
        session.close();
        return result;
    }
}
