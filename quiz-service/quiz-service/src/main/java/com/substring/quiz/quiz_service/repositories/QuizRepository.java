package com.substring.quiz.quiz_service.repositories;

import com.substring.quiz.quiz_service.collections.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends MongoRepository<Quiz,String> {

    List<Quiz> findByTitle(String title);
    List<Quiz> findByCategoryId(String categoryId);
}
