package com.yuklid.question_service.controller;

import com.yuklid.question_service.dto.QuestionRequestDTO;
import com.yuklid.question_service.dto.QuestionResponseDTO;
import com.yuklid.question_service.model.Question;
import com.yuklid.question_service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO dto) {
        QuestionResponseDTO saved = questionService.createQuestion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable UUID id) {
        QuestionResponseDTO question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/random")
    public ResponseEntity<QuestionResponseDTO> getRandomQuestion(){
        QuestionResponseDTO question = questionService.getRandomQuestion();
        return ResponseEntity.ok(question);
    }


}
