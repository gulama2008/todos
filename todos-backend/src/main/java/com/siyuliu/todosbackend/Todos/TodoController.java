package com.siyuliu.todosbackend.Todos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoCreateDTO data) {
        Todo newTodo = this.todoService.createTodo(data);
        if (newTodo == null) {
            System.out.println("no such category");
            return null;
        }
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteById(@PathVariable Long id) {
        boolean deleted = this.todoService.deleteById(id);
        if (deleted == true) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        System.out.println("no such id");
            return null;
    }

}
