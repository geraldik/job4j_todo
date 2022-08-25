package ru.job4j.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    private LocalDateTime created = LocalDateTime.now();

    private boolean done;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    public Item(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Item(String description) {
        this.description = description;
    }


    public Item(String name, String description, LocalDateTime created, boolean done) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
    }
}