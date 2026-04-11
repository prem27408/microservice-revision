package com.substring.quiz.quiz_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDto {

    private String id;
    private String title;
    private String description;
    private Integer maxMarks;
    private Integer timeLimit;
    private String createdBy;
    private Integer noOfQuestions;
    private String imageUrl;
    private Boolean live;
    private Integer passingMarks;
    private String categoryId;
    private CategoryDto categoryDto;

}
