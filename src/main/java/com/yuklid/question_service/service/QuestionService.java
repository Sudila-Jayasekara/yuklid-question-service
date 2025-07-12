package com.yuklid.question_service.service;

import com.yuklid.question_service.exception.QuestionNotFoundException;
import com.yuklid.question_service.model.Question;
import com.yuklid.question_service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions available");
        }
        // Randomly select a question from the list
        int randomIndex = (int) (Math.random() * questions.size());
        return questions.get(randomIndex);
    }

}
