package com.substring.quiz.category.category_service.service;

import com.substring.quiz.category.category_service.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);

    CategoryDto getCategory(String categoryId);

    void deleteCategory(String categoryId);

    List<CategoryDto> getAllCategories();


}
