package com.example.characterdevelopment.controller;

import com.example.characterdevelopment.model.Category;
import com.example.characterdevelopment.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomepageController {

    private CategoriesService categoriesService;

    @Autowired
    public HomepageController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("categories", categoriesService.findAll());
        return "home";
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", categoriesService.findById(id));
        model.addAttribute("childCategories", categoriesService.findAllChildCategories(id));
        return "category/show";
    }

    @GetMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category, Model model) {
        model.addAttribute("categories", categoriesService.findAll());
        return "category/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("category") Category category) {
        if (category.getParentId() != null)
            category.setHierarchyLevel(categoriesService.findById(category.getParentId()).getHierarchyLevel() + 1);
        categoriesService.save(category);
        return "redirect:/home";
    }

    @PostMapping("/{id}")
    public String delete(@ModelAttribute("category") Category category) {
        categoriesService.deleteWithChildren(category);
        return "redirect:/home";
    }
}
