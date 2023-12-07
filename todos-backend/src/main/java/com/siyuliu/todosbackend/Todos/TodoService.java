package com.siyuliu.todosbackend.Todos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyuliu.todosbackend.Categories.Category;
import com.siyuliu.todosbackend.Categories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    public Todo createTodo(TodoCreateDTO data) {
        Long id = data.getCategoryId();
        Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isPresent()) {
            String content = data.getContent();
            Todo newTodo = new Todo(content, category.get());
            Todo created = this.todoRepository.save(newTodo);
            return created;
        }
        return null;
        
    }

}
