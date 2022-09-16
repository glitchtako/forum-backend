package com.glitchtako.forum.service.impl;

import com.glitchtako.forum.exception.CategoryNotFoundException;
import com.glitchtako.forum.model.entity.Category;
import com.glitchtako.forum.model.request.CreateCategoryRequest;
import com.glitchtako.forum.model.request.UpdateCategoryRequest;
import com.glitchtako.forum.repository.CategoryRepository;
import com.glitchtako.forum.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category createCategory(CreateCategoryRequest request) {

        Category category = this.categoryRepository.save(
                Category.builder()
                        .name(request.getName())
                        .build());

        return category;
    }

    @Override
    public Category updateCategory(Long categoryId, UpdateCategoryRequest request) throws CategoryNotFoundException {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        category.setName(request.getName());
        return categoryRepository.save(category);
    }


}
