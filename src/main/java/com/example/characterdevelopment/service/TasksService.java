package com.example.characterdevelopment.service;

import com.example.characterdevelopment.model.Category;
import com.example.characterdevelopment.model.Task;
import com.example.characterdevelopment.repository.CategoriesRepository;
import com.example.characterdevelopment.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    TasksRepository tasksRepository;

    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Task> findAll() {
        return tasksRepository.findAll();
    }

    public Task findById(int id) {
        Optional<Task> result = tasksRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public void save(Task task) {
        tasksRepository.save(task);
    }

    @Transactional
    public void update(Task task) {
        tasksRepository.save(task);
    }

    @Transactional
    public void delete(Task task) {
        tasksRepository.delete(task);
    }
}
