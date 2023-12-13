package com.siyuliu.todosbackend.todos;

import com.siyuliu.todosbackend.categories.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private boolean isArchived=false;

    @Column
    private boolean isCompleted=false;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    
    public Todo() {
    }
    
    public Todo(String content) {
        this.content = content;
    }

    public Todo(String content, Category category) {
        this.content = content;
        this.category = category;
    }
    
    public Todo(String content, boolean isArchived, boolean isCompleted, Category category) {
        this.content = content;
        this.isArchived = isArchived;
        this.isCompleted = isCompleted;
        this.category = category;
    }
    
}
