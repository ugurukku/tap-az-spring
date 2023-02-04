package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.entities.Category;
import com.ugurukku.tapazspring.exceptions.product.CategoryNotFoundException;
import com.ugurukku.tapazspring.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public void saveAll(List<Category> categories){
        repository.saveAll(categories);
    }

    public Category findCategoryByName(String categoryName) {
        return repository
                .findCategoryByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"+categoryName));
    }

    public List<Category> getAll(){
        return repository.findAll();
    }
}
