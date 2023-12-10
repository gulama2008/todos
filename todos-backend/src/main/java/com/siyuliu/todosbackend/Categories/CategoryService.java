package com.siyuliu.todosbackend.Categories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyuliu.todosbackend.Todos.Todo;
import com.siyuliu.todosbackend.Todos.TodoCreateDTO;
import com.siyuliu.todosbackend.Todos.TodoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TodoRepository todoRepository;

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category createCategory(CategoryCreateDTO data) {
        String name = data.getName();
        Category newCategory = new Category(name);
        Category created = this.categoryRepository.save(newCategory);
        return created;
    }

    public boolean deleteById(Long id) {
        Optional<Category> foundCategory = this.categoryRepository.findById(id);
        if (foundCategory.isPresent()) {
            List<Todo> todos = todoRepository.findByCategory(foundCategory.get());
            if(todos.size()!=0){
                for (Todo todo : todos) {
                    todo.setCategory(null);
                    this.todoRepository.save(todo);
                }
            }
            this.categoryRepository.delete(foundCategory.get());
            return true;
        }
        return false;
    }
}
