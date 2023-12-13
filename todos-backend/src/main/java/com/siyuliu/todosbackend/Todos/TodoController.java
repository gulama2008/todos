package com.siyuliu.todosbackend.todos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siyuliu.todosbackend.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAll() {
        List<Todo> allTodos = this.todoService.getAll();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
	public ResponseEntity<Todo> getById(@PathVariable Long id) {
		Optional<Todo> found = this.todoService.getById(id);
		
		if(found.isPresent()) {
			return new ResponseEntity<Todo>(found.get(), HttpStatus.OK);
		}
		throw new NotFoundException(String.format("Todo with id: %d does not exist", id));
	}
    
    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoCreateDTO data) {
        Todo newTodo = this.todoService.createTodo(data);
        if (newTodo == null) {
            throw new NotFoundException(String.format("Could not found category with id %d", data.getCategoryId()));
        }
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteById(@PathVariable Long id) {
        boolean deleted = this.todoService.deleteById(id);
        if (deleted == true) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        throw new NotFoundException(String.format("Todo with id %d does not exist, could not delete", id));
    }
    
    @PatchMapping("/{id}")
	public ResponseEntity<Todo> updateById(@PathVariable Long id, 
            @Valid @RequestBody TodoUpdateDTO data) {
                System.out.println("test1");
		Optional<Todo> updated = this.todoService.updateById(id, data);
		if(updated.isPresent()) {
			return new ResponseEntity<Todo>(updated.get(), HttpStatus.OK);
		}
        throw new NotFoundException(String.format("Todo with id %d does not exist, could not update", id));
	}

}
