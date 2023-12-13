package com.siyuliu.todosbackend.todos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateDTO {

    @NotBlank
    private String content;

    private boolean archived;

    private boolean completed;

    private Long categoryId;
    
}
