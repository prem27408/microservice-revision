package com.substring.quiz.quiz_service.services;

import com.substring.quiz.quiz_service.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="category-service",url="http://localhost:9091/api/v1/")
public interface CategoryFeignService {

    //get all categories
    @GetMapping("/categories")
    List<CategoryDto> findAll();

    //get single category
    @GetMapping("/categories/{categoryId}")
    CategoryDto findById(@PathVariable  String categoryId);

    //create new Category
    @PostMapping("/categories")
    CategoryDto create(@RequestBody CategoryDto categoryDto);

    @PutMapping("/categories/{categoryId}")
    CategoryDto update(@PathVariable String categoryId,@RequestBody CategoryDto categoryDto);

    @DeleteMapping("/categories/{categoryId}")
    void delete(@PathVariable String categoryId);

}
