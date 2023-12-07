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

    public Optional<Todo> getById(Long id) {
		Optional<Todo> foundTodo = todoRepository.findById(id);
		return foundTodo;
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

    public boolean deleteById(Long id) {
		// check if what I want to delete exists
		Optional<Todo> foundTodo = this.todoRepository.findById(id);
		// if it exists call some repository method that deletes it
		// return true so it's easy to handle in the controller
		if(foundTodo.isPresent()) {
			this.todoRepository.delete(foundTodo.get());
			return true;
		}
		
		return false;
		
		// if it doesn't exist, return false
	}

}
