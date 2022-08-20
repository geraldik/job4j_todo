package ru.job4j.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"created", "description"})
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());

    private boolean done;

    public Item(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Item(String description) {
        this.description = description;
    }


    public Item(String name, String description, Timestamp created, boolean done) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
    }
}