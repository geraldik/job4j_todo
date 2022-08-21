package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final static String MESSAGE = "Приложение \"TODO список\". Примененный стек технологий: "
           + "Spring boot, Thymeleaf, Bootstrap, Hibernate, PostgreSql.";

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        SessionControl.getUserSession(model, session);
        model.addAttribute("message", MESSAGE);
        return "index";
    }
}



