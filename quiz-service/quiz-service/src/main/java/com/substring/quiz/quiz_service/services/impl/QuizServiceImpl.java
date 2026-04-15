package com.substring.quiz.quiz_service.services.impl;

import com.substring.quiz.quiz_service.collections.Quiz;
import com.substring.quiz.quiz_service.dto.CategoryDto;
import com.substring.quiz.quiz_service.dto.QuizDto;
import com.substring.quiz.quiz_service.repositories.QuizRepository;
import com.substring.quiz.quiz_service.services.CategoryService;
import com.substring.quiz.quiz_service.services.QuizService;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;
    private ModelMapper modelMapper;
    private Logger logger= LoggerFactory.getLogger(QuizServiceImpl.class);
    private RestTemplate restTemplate;
    private final CategoryService categoryService;

    public QuizServiceImpl(CategoryService categoryService, ModelMapper modelMapper, QuizRepository quizRepository, RestTemplate restTemplate) {
        this.categoryService = categoryService;

        this.modelMapper = modelMapper;
        this.quizRepository = quizRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public QuizDto create(QuizDto quizDto) {
//        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
//        quiz.setId(UUID.randomUUID().toString());
//        return modelMapper.map(quizRepository.save(quiz),QuizDto.class);

        // Step 1: Call Category Service (validate + fetch category)
        String categoryId = quizDto.getCategoryId();
        String url = "http://localhost:9091/api/v1/categories/" + categoryId;

        CategoryDto category = restTemplate.getForObject(url, CategoryDto.class);

        if (category == null) {
            throw new RuntimeException("Category not found: " + categoryId);
        }

        // Step 2: Save Quiz
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        quiz.setId(UUID.randomUUID().toString());

        Quiz savedQuiz = quizRepository.save(quiz);

        // Step 3: Convert to DTO
        QuizDto response = modelMapper.map(savedQuiz, QuizDto.class);

        // Step 4: Set category response
        response.setCategoryDto(category);

        return response;
    }

    @Override
    public QuizDto update(String quizId, QuizDto quizDto) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("quiz not found:" + quizId));

        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setMaxMarks(quizDto.getMaxMarks());
        quiz.setTimeLimit(quizDto.getTimeLimit());
        quizDto.setCreatedBy(quizDto.getCreatedBy());
        quizDto.setNoOfQuestions(quizDto.getNoOfQuestions());
        quizDto.setImageUrl(quizDto.getImageUrl());
        quiz.setLive(quizDto.getLive());
        quiz.setPassingMarks(quizDto.getPassingMarks());
        quiz.setCategoryId(quizDto.getCategoryId());

        return modelMapper.map(quizRepository.save(quiz),QuizDto.class);
    }

    @Override
    public void delete(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("quiz not found:" + quizId));
        quizRepository.delete(quiz);

    }

    @Override
    public List<QuizDto> findAll() {
        List<Quiz> quizes = quizRepository.findAll();

        //getting category of all quiz
        List<QuizDto> list = quizes.stream().map(quiz -> {

            String categoryId = quiz.getCategoryId();
            QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
//            try {
//                CategoryDto category = this.webClient
//                        .get()
//                        .uri("/api/v1/categories/{categoryId}", categoryId)
//                        .retrieve()//to receive the value
//                        .bodyToMono(CategoryDto.class)
//                        .block();//blocking nature {here thread will not wait}{non reactive}
//
//
//                quizDto.setCategoryDto(category);
//
//            }catch(WebClientResponseException ex){
//
//                if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND)){
//                    logger.error("category not found");
//                }else if(ex.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)){
//                        logger.info("Internal Server error");
//                }
//                quizDto.setCategoryDto(null);
//                ex.printStackTrace();
//            }
            CategoryDto categoryDto = this.categoryService.findById(categoryId);
            quizDto.setCategoryDto(categoryDto);
            return quizDto;

        }).toList();

        return list;
    }

    @Override
    public QuizDto findById(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("quiz not found:" + quizId));

        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        String categoryId=quiz.getCategoryId();
        if (categoryId == null) {
            throw new RuntimeException("Category not found: " + categoryId);
        }
        String url="http://localhost:9091/api/v1/categories"+categoryId;

        logger.info(url);
        CategoryDto category = restTemplate.getForObject(url, CategoryDto.class);
        quizDto.setCategoryDto(category);
        return quizDto;

    }

    @Override
    public List<QuizDto> findByCategory(String categoryId) {
        List<Quiz> quizes = quizRepository.findByCategoryId(categoryId);
        return quizes.stream().map(quize->modelMapper.map(quize,QuizDto.class)).toList();

    }
}
