package com.siyuliu.todosbackend.Todos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

}
