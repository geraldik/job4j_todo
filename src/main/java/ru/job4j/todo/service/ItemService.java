package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.entity.Account;
import ru.job4j.todo.entity.Item;
import ru.job4j.todo.store.ItemStore;

import java.util.Collection;

@Service
public class ItemService {

    private final ItemStore store;

    public ItemService(ItemStore store) {
        this.store = store;
    }

    public Item create(Item item) {
        return store.create(item);
    }

    public void update(Item item) {
        store.update(item);
    }

    public void delete(Item item) {
        store.delete(item);
    }

    public void setItemIsDone(Item item) {
        store.setItemIsDone(item);
    }

    public Collection<Item> findAll(Account account) {
        return store.findAll(account);
    }

    public Collection<Item> findNew(Account account) {
        return store.findNew(account);
    }

    public Collection<Item> findCompleted(Account account) {
        return store.findCompleted(account);
    }

    public Item findById(int id) {
        return store.findById(id);
    }
}

