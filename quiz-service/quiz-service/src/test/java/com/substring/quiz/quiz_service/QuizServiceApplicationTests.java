package com.substring.quiz.quiz_service;

import com.substring.quiz.quiz_service.dto.CategoryDto;
import com.substring.quiz.quiz_service.services.CategoryFeignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QuizServiceApplicationTests {

//	@Autowired
//	private CategoryFeignService categoryFeignService;

//	@Test
//	public void testFeignAllCategories(){
//		System.out.println("getting all categories");
//		List<CategoryDto> all = categoryFeignService.findAll();
//		all.forEach(categoryDto -> System.out.println(categoryDto.getTitle()));
//
////		Assertions.assertEquals(3,all.size());
//		Assertions.assertNotNull(all);
//	}

//	@Test
//	public void testFeignSingleCategory(){
//		System.out.println("Getting single category");
//		CategoryDto categoryDto=categoryFeignService.findById("d75056dc-ed67-4809-bbc7-4a9f1389436d");
//		System.out.println(categoryDto.getTitle());
//		Assertions.assertNotNull(categoryDto);
//	}

}
