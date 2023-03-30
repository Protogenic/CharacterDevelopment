package com.example.characterdevelopment.controller;

import com.example.characterdevelopment.dao.CategoryDAO;
import com.example.characterdevelopment.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomepageController {

    private CategoryDAO categoryDAO;

    @Autowired
    public HomepageController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping()
    public String homePage(Model model) {
        model.addAttribute("categories", categoryDAO.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "add";
    }

    @PostMapping()
    public String create(@ModelAttribute("category") Category category) {
        categoryDAO.save(category);
        return "redirect:/home";
    }

}
