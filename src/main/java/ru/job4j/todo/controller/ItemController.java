package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.entity.Item;
import ru.job4j.todo.service.ItemService;



@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/allItems")
    public String allItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "allItems";
    }

    @GetMapping("/completedItems")
    public String completedItems(Model model) {
        model.addAttribute("items", itemService.findCompleted());
        return "completedItems";
    }

    @GetMapping("/newItems")
    public String items(Model model) {
        model.addAttribute("items", itemService.findNew());
        return "newItems";
    }


    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item) {
        itemService.create(item);
        return "redirect:/allItems";
    }

    @GetMapping("/formUpdateItem/{itemId}")
    public String formUpdateItem(Model model, @PathVariable("itemId") int id) {
        model.addAttribute("item", itemService.findById(id));
        return "updateItem";
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item) {
        itemService.update(item);
        return "redirect:/allItems";
    }

    @GetMapping("/formAddItem")
    public String formAddPost() {
        return "addItem";
    }

    @GetMapping("/complete/{itemId}")
    public String completeItem(@PathVariable("itemId") int id) {
        itemService.setItemIsDone(itemService.findById(id));
        return "redirect:/allItems";
    }

    @GetMapping("/delete/{itemId}")
        public String deleteItem(@PathVariable("itemId") int id) {
        itemService.delete(itemService.findById(id));
        return "redirect:/allItems";
    }

    @GetMapping("/itemDetails/{itemId}")
    public String itemDetails(@PathVariable("itemId") int id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "itemDetails";
    }
}