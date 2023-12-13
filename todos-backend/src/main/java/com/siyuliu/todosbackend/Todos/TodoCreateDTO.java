package com.siyuliu.todosbackend.todos;
import com.siyuliu.todosbackend.categories.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateDTO {
    private String content;
    private Long categoryId;
}
