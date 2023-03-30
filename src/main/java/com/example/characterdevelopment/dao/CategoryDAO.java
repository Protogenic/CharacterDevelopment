package com.example.characterdevelopment.dao;

import com.example.characterdevelopment.model.Category;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    public void save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(category);
    }
}
