package ru.job4j.todo.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.entity.Category;
import ru.job4j.todo.entity.Item;
import ru.job4j.todo.utility.TransactionService;

import java.util.List;


@Repository
public class CategoryStore implements TransactionService {

    private final SessionFactory sf;
    private static final String QUERY_FIND_ALL = "from Category c";
    private static final String QUERY_FIND_BY_ID = "from Category c where c.id = :fId";

    public CategoryStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Category> findAll() {
        return this.tx(
                session -> session.createQuery(QUERY_FIND_ALL)
                        .list(),
                sf
        );
    }

    public Category findById(final int id) {
        return this.tx(
                session -> session.createQuery(QUERY_FIND_BY_ID, Category.class)
                        .setParameter("fId", id)
                        .uniqueResult(),
                sf
        );
    }

}
