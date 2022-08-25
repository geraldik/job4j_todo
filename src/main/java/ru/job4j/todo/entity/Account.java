package ru.job4j.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    private String name;

    private String login;

    private String password;

    @OneToMany(mappedBy = "account")
    private List<Item> items = new ArrayList<>();

    public Account(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

}

