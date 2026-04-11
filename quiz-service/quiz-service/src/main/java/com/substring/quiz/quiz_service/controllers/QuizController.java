package com.substring.quiz.quiz_service.controllers;

import com.substring.quiz.quiz_service.dto.QuizDto;
import com.substring.quiz.quiz_service.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizDto> create(@RequestBody QuizDto quizDto){
        return new ResponseEntity<>(quizService.create(quizDto), HttpStatus.CREATED);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDto> update(@PathVariable("quizId") String quizId,@RequestBody QuizDto quizDto){
        return new ResponseEntity<>(quizService.update(quizId,quizDto),HttpStatus.OK);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> update(@PathVariable("quizId") String quizId){
        quizService.delete(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> getAll(){
        return new ResponseEntity<>(this.quizService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDto> getById(@PathVariable("quizId") String quizId){
        return new ResponseEntity<>(quizService.findById(quizId),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<QuizDto>> findByCategory(@PathVariable("categoryId") String categoryId){
        return new ResponseEntity<>(this.quizService.findByCategory(categoryId),HttpStatus.OK);
    }


}
