package com.substring.quiz.quiz_service.collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "quizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    private String id;

    @Field("quiz-title")
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

}
