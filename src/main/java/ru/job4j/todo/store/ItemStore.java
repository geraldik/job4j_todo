package ru.job4j.todo.store;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.todo.entity.Account;
import ru.job4j.todo.entity.Item;

import java.util.List;
import java.util.function.Function;

@Repository
public class ItemStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item create(final Item save) {
        return this.tx(
                session -> {
                    session.save(save);
                    return save;
                }
        );
    }

    public void update(final Item item) {
        this.tx(
                session -> {
                    session.update(item);
                    return session;
                }
        );
    }

    public void delete(final Item item) {
        this.tx(
                session -> {
                    session.delete(item);
                    return session;
                }
        );
    }

    public List<Item> findAll(final Account account) {
        return this.tx(
                session -> session.createQuery("from Item  i where i.account = :account", Item.class)
                        .setParameter("account", account)
                        .list()
        );
    }

    public List<Item> findNew(final Account account) {
        return this.tx(
                session -> session.createQuery("from Item i where i.done = false and i.account = :account",
                                Item.class)
                        .setParameter("account", account)
                        .list()
        );
    }

    public List<Item> findCompleted(final Account account) {
        return this.tx(
                session -> session.createQuery("from Item i where i.done = true and i.account = :account",
                                Item.class)
                        .setParameter("account", account)
                        .list()
        );
    }

    public Item findById(final int id) {
        return this.tx(
                session -> session.createQuery("from Item i where i.id = :fId", Item.class)
                        .setParameter("fId", id)
                        .uniqueResult()
        );
    }

    public void completeItem(final int id) {
        this.tx(
                session ->
                    session.createQuery("update Item i set i.done = true where i.id=:fId")
                            .setParameter("fId", id)
                            .executeUpdate()
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