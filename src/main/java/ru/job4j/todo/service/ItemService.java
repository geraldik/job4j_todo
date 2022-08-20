package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
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

    public Collection<Item> findAll() {
        return store.findAll();
    }

    public Collection<Item> findNew() {
        return store.findNew();
    }

    public Collection<Item> findCompleted() {
        return store.findCompleted();
    }

    public Item findById(int id) {
        return store.findById(id);
    }
}

