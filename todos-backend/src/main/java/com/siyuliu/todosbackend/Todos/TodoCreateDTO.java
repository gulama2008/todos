package com.siyuliu.todosbackend.Todos;
import com.siyuliu.todosbackend.Categories.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateDTO {
    private String content;
    private Long categoryId;
}
