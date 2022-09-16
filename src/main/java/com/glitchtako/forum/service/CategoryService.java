package com.glitchtako.forum.service;

import com.glitchtako.forum.exception.CategoryNotFoundException;
import com.glitchtako.forum.model.entity.Category;
import com.glitchtako.forum.model.request.CreateCategoryRequest;
import com.glitchtako.forum.model.request.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

  List<Category> getAllCategories();

  Category createCategory(CreateCategoryRequest request);

  Category updateCategory(Long categoryId, UpdateCategoryRequest request)
      throws CategoryNotFoundException;
}
