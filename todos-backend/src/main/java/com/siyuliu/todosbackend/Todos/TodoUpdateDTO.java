package com.siyuliu.todosbackend.Todos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateDTO {

    @NotBlank
    private String content;

    private boolean isArchived;

    private boolean isCompleted;

    private Long categoryId;
    
}
