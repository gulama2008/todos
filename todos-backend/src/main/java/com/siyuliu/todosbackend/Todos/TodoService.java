package com.siyuliu.todosbackend.todos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyuliu.todosbackend.categories.Category;
import com.siyuliu.todosbackend.categories.CategoryRepository;

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
        if (id == 0) {
            String content = data.getContent();
            Todo newTodo = new Todo(content);
            Todo created = this.todoRepository.save(newTodo);
            return created;
        }
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
        Optional<Todo> foundTodo = this.todoRepository.findById(id);
        if (foundTodo.isPresent()) {
            System.out.println("TEST DELETE");
            foundTodo.get().setArchived(true);
            // this.todoRepository.delete(foundTodo.get());
            return true;
        }
        return false;
    }
    
    public Optional<Todo> updateById(Long id, TodoUpdateDTO data) {
        Optional<Todo> foundTodo = this.getById(id);
        System.out.println(foundTodo.get());
        if (foundTodo.isPresent()) {
            Todo toUpdate = foundTodo.get();
            toUpdate.setContent(data.getContent());
            toUpdate.setArchived(data.isArchived());
            toUpdate.setCompleted(data.isCompleted());
            if (data.getCategoryId() != 0
                    && (toUpdate.getCategory() == null || toUpdate.getCategory().getId() != data.getCategoryId())) {
                Long categoryId = data.getCategoryId();
                Optional<Category> category = categoryRepository.findById(categoryId);
                if (category.isPresent()) {
                    toUpdate.setCategory(category.get());
                    Todo updatedTodo = this.todoRepository.save(toUpdate);
                    return Optional.of(updatedTodo);
                } else {
                    return Optional.of(null);
                }
            }else if(data.getCategoryId() == 0&&toUpdate.getCategory() != null){
                toUpdate.setCategory(null);
                Todo updatedTodo = this.todoRepository.save(toUpdate);
                return Optional.of(updatedTodo);
            }else {
                Todo updatedTodo = this.todoRepository.save(toUpdate);
                return Optional.of(updatedTodo);
            }
        }
        return Optional.of(null);
    }

}
