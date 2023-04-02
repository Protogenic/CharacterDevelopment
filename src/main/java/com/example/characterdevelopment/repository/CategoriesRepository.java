package com.example.characterdevelopment.repository;

import com.example.characterdevelopment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoryByParentId(Integer parentId);

    List<Category> findCategoryByHierarchyLevelGreaterThan(Integer hierarchyLevel);
}
