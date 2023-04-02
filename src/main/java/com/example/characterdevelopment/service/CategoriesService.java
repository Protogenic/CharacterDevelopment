package com.example.characterdevelopment.service;

import com.example.characterdevelopment.model.Category;
import com.example.characterdevelopment.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoriesService {

    CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    public Category findById(int id) {
        Optional<Category> result = categoriesRepository.findById(id);
        return result.orElse(null);
    }

    public List<Category> findAllChildCategories(Integer parentId) {
        return categoriesRepository.findCategoryByParentId(parentId);
    }

    public List<Category> findCategoryByHierarchyLevelGreaterThan(Integer hierarchyLevel) {
        return categoriesRepository.findCategoryByHierarchyLevelGreaterThan(hierarchyLevel);
    }

    @Transactional
    public void save(Category category) {
        categoriesRepository.save(category);
    }

    @Transactional
    public void update(Category category) {
        categoriesRepository.save(category);
    }

    @Transactional
    public void delete(Category category) {
        categoriesRepository.delete(category);
    }

    @Transactional
    public void deleteWithChildren(Category category) {
        for(Category c: findAllChildCategories(category.getId())) {
            categoriesRepository.delete(c);
        }

        categoriesRepository.delete(category);
    }
}
