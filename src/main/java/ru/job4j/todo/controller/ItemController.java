package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.entity.Account;
import ru.job4j.todo.entity.Category;
import ru.job4j.todo.entity.Item;
import ru.job4j.todo.service.CategoryService;
import ru.job4j.todo.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/allItems")
    public String allItems(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        SessionControl.getUserSession(model, session);
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("items", itemService.findAll(account));
        model.addAttribute("account", account);
        return "allItems";
    }

    @GetMapping("/completedItems")
    public String completedItems(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        SessionControl.getUserSession(model, session);
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("items", itemService.findCompleted(account));
        model.addAttribute("account", account);
        return "completedItems";
    }

    @GetMapping("/newItems")
    public String items(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        SessionControl.getUserSession(model, session);
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("items", itemService.findNew(account));
        model.addAttribute("account", account);
        return "newItems";
    }


    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item, Model model, HttpServletRequest req,
                             @RequestParam(value = "category.id", required = false) List<Integer> categoriesId) {
        Set<Category> categories = new HashSet<>();
        for (Integer categoryId : categoriesId) {
            categories.add(categoryService.findById(categoryId));
        }
        item.setCategories(categories);
        HttpSession session = req.getSession();
        SessionControl.getUserSession(model, session);
        Account account = (Account) session.getAttribute("account");
        item.setAccount(account);
        itemService.create(item);
        return "redirect:/allItems";
    }

    @GetMapping("/formUpdateItem/{itemId}")
    public String formUpdateItem(@PathVariable("itemId") int id, Model model, HttpSession session) {
        SessionControl.getUserSession(model, session);
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "updateItem";
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item,  Model model, HttpServletRequest req,
                             @RequestParam(value = "category.id", required = false) List<Integer> categoriesId) {
        HttpSession session = req.getSession();
        SessionControl.getUserSession(model, session);
        Account account = (Account) session.getAttribute("account");
        Set<Category> categories = new HashSet<>();
        for (Integer categoryId : categoriesId) {
            categories.add(categoryService.findById(categoryId));
        }
        item.setCategories(categories);
        item.setAccount(account);
        itemService.update(item);
        return "redirect:/allItems";
    }

    @GetMapping("/formAddItem")
    public String formAddPost(Model model, HttpSession session) {
        SessionControl.getUserSession(model, session);
        model.addAttribute("categories", categoryService.findAll());
        return "addItem";
    }

    @PostMapping("/complete/{itemId}")
    public String completeItem(@PathVariable("itemId") int id, Model model, HttpSession session) {
        SessionControl.getUserSession(model, session);
        itemService.completeItem(id);
        return "redirect:/allItems";
    }

    @PostMapping("/delete/{itemId}")
        public String deleteItem(@PathVariable("itemId") int id) {
        itemService.delete(itemService.findById(id));
        return "redirect:/allItems";
    }

    @GetMapping("/itemDetails/{itemId}")
    public String itemDetails(@PathVariable("itemId") int id, Model model, HttpSession session) {
        SessionControl.getUserSession(model, session);
        model.addAttribute("item", itemService.findById(id));
        return "itemDetails";
    }
}