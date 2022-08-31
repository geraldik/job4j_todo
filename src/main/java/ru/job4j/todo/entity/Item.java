package ru.job4j.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_id")
    private int id;

    private String name;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date(System.currentTimeMillis());

    private boolean done;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "items_category",
            joinColumns = { @JoinColumn(name = "items_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private Set<Category> categories = new HashSet<>();
    public Item(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Item(String description) {
        this.description = description;
    }


    public Item(String name, String description, Date created, boolean done) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
    }
}