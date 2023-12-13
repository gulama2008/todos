package com.siyuliu.todosbackend.categories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siyuliu.todosbackend.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> allCategories = this.categoryService.getAll();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryCreateDTO data) {
        Category newCategory = this.categoryService.createCategory(data);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        boolean deleted = this.categoryService.deleteById(id);
        if (deleted == true) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        throw new NotFoundException(String.format("Category with id %d does not exist, could not delete", id));
    }

    @PutMapping("/{id}")
	public ResponseEntity<Category> updateById(@PathVariable Long id, 
            @Valid @RequestBody CategoryUpdateDTO data) {
		Optional<Category> updated = this.categoryService.updateById(id, data);
        if (updated.isPresent()) {
			return new ResponseEntity<Category>(updated.get(), HttpStatus.OK);
		}
        throw new NotFoundException(String.format("Category with id %d does not exist, could not update", id));
	}
}
