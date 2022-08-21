package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.todo.entity.Account;
import ru.job4j.todo.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AccountController {
    private static final String FAIL_MESSAGE = "Пользователь с такой почтой уже существует";
    private static final String SUCCESS_MESSAGE = "Пользователь зарегестрирован успешно";

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Account account) {
        Optional<Account> regAcc = accountService.create(account);
        if (regAcc.isEmpty()) {
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/formRegistration")
    public String formRegistration(Model model, HttpSession session) {
        SessionControl.getUserSession(model, session);
        return "registration";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("message", SUCCESS_MESSAGE);
        return "success";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        model.addAttribute("message", FAIL_MESSAGE);
        return "fail";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Account account, HttpServletRequest req) {
        Optional<Account> accDb = accountService.findByLoginAndPas(
                account.getLogin(), account.getPassword()
        );
        if (accDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("account", accDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
