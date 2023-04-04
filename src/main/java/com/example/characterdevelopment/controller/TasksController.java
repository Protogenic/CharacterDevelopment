package com.example.characterdevelopment.controller;

import com.example.characterdevelopment.model.Category;
import com.example.characterdevelopment.model.Task;
import com.example.characterdevelopment.service.CategoriesService;
import com.example.characterdevelopment.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TasksController {
    private TasksService tasksService;
    private CategoriesService categoriesService;

    @Autowired
    public TasksController(TasksService tasksService, CategoriesService categoriesService) {
        this.tasksService = tasksService;
        this.categoriesService = categoriesService;
    }

    @GetMapping()
    public String taskList(Model model) {
        model.addAttribute("tasks", tasksService.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String addTask(@ModelAttribute("task") Task task, Model model) {
        model.addAttribute("categories", categoriesService.findAll());
        return "task/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("task") Task task) {
        tasksService.save(task);
        return "redirect:/home";
    }

    @PostMapping("/{id}")
    public String done(@PathVariable("id") int id) {
        Task task = tasksService.findById(id);
        if(task.getCategory() != null) {
            categoriesService.addLevels(categoriesService.findById(task.getCategory().getId()));
        }

        tasksService.delete(task);
        return "redirect:/home";
    }
}
