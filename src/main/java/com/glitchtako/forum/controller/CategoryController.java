package com.glitchtako.forum.controller;

import com.glitchtako.forum.exception.CategoryNotFoundException;
import com.glitchtako.forum.model.entity.Category;
import com.glitchtako.forum.model.request.CreateCategoryRequest;
import com.glitchtako.forum.model.request.UpdateCategoryRequest;
import com.glitchtako.forum.model.response.RestResponse;
import com.glitchtako.forum.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @GetMapping(value = "/categories")
  public RestResponse<List<Category>> getAllCategories() {
    return RestResponse.ok(this.categoryService.getAllCategories());
  }

  @PostMapping(value = "/category")
  public RestResponse<Category> createCategory(@RequestBody CreateCategoryRequest request) {
    return RestResponse.ok(this.categoryService.createCategory(request));
  }

  @PutMapping(value = "/category/{id}")
  public RestResponse<Category> updateCategory(
      @PathVariable(value = "id") Long categoryId, @RequestBody UpdateCategoryRequest request)
      throws CategoryNotFoundException {
    return RestResponse.ok(this.categoryService.updateCategory(categoryId, request));
  }
}
