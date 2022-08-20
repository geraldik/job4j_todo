package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final static String MESSAGE = "Приложение \"TODO список\". Примененный стек технологий: "
           + "Spring boot, Thymeleaf, Bootstrap, Hibernate, PostgreSql.";

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", MESSAGE);
        return "index";
    }
}



