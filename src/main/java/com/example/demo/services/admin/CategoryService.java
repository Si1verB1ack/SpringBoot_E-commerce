package com.example.demo.services.admin;

import com.example.demo.models.Category;
import com.example.demo.repositories.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }
}

