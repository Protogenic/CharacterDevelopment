package com.example.characterdevelopment.controller;

import com.example.characterdevelopment.model.Category;
import com.example.characterdevelopment.service.CategoriesService;
import com.example.characterdevelopment.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomepageController {

    private CategoriesService categoriesService;
    private TasksService tasksService;

    @Autowired
    public HomepageController(CategoriesService categoriesService, TasksService tasksService) {
        this.categoriesService = categoriesService;
        this.tasksService = tasksService;
    }

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("tasks", tasksService.findAll());
        return "home";
    }
}
