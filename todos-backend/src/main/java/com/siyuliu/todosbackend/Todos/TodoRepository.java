package com.siyuliu.todosbackend.Todos;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.siyuliu.todosbackend.Categories.Category;


public interface TodoRepository extends JpaRepository<Todo,Long>{
   List<Todo> findByCategory(Category category);
}
