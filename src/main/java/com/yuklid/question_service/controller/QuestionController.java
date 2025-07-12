package com.yuklid.question_service.controller;

import com.yuklid.question_service.dto.QuestionOptionDTO;
import com.yuklid.question_service.model.Question;
import com.yuklid.question_service.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<QuestionOptionDTO> createQuestion(@Valid @RequestBody QuestionOptionDTO dto) {
        QuestionOptionDTO saved = questionService.createQuestion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionOptionDTO> getQuestionById(@PathVariable UUID id) {
        QuestionOptionDTO question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionOptionDTO>> getAllQuestions() {
        List<QuestionOptionDTO> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }



}
