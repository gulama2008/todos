package com.siyuliu.todosbackend.todos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siyuliu.todosbackend.categories.Category;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo,Long>{
   List<Todo> findByCategory(Category category);
}
