package ru.job4j.todo.controller;

import org.springframework.ui.Model;
import ru.job4j.todo.entity.Account;

import javax.servlet.http.HttpSession;

public class SessionControl {

    public static void getUserSession(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            account = new Account();
            account.setName("Гость");
        }
        model.addAttribute("account", account);
    }
}