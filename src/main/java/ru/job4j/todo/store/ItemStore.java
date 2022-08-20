package ru.job4j.todo.store;

import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.todo.entity.Item;

import java.util.List;

@Repository
public class ItemStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item create(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void update(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public void setItemIsDone(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        item.setDone(true);
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findNew() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item i where i.done = false").list();
        session.close();
        return result;
    }

    public List<Item> findCompleted() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item i where i.done = true").list();
        session.close();
        return result;
    }

    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item i where i.id = :fId")
                .setParameter("fId", id);
        Item result = (Item) query.uniqueResult();
        session.close();
        return result;
    }
}