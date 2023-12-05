package com.siyuliu.todosbackend.Todos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long>{
    
}
