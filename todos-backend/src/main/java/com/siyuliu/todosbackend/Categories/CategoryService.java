package com.siyuliu.todosbackend.Categories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyuliu.todosbackend.Todos.Todo;
import com.siyuliu.todosbackend.Todos.TodoCreateDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category createCategory(CategoryCreateDTO data) {
        String name = data.getName();
        Category newCategory = new Category(name);
        Category created = this.categoryRepository.save(newCategory);
        return created;
    }
}
