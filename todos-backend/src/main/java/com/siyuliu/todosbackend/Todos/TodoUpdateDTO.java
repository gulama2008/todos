package com.siyuliu.todosbackend.Todos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateDTO {

    @NotBlank
    private String content;

    @NotBlank
    private boolean isArchived;

    @NotBlank
    private boolean isCompleted;

    @NotBlank
    private Long categoryId;
    
}
