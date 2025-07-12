package com.yuklid.question_service.controller;

import com.yuklid.question_service.model.Question;
import com.yuklid.question_service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Question service is running");
    }

    @GetMapping("/random")
    private ResponseEntity<Question> getRandomQuestion() {
        Question question = questionService.getRandomQuestion();
        return ResponseEntity.ok(question);
    }

    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(201).body(createdQuestion);
    }

}
