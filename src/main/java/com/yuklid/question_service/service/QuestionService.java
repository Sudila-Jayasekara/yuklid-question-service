package com.yuklid.question_service.service;

import com.yuklid.question_service.dto.QuestionOptionDTO;
import com.yuklid.question_service.exception.QuestionNotFoundException;
import com.yuklid.question_service.model.Option;
import com.yuklid.question_service.model.Question;
import com.yuklid.question_service.repository.OptionRepository;
import com.yuklid.question_service.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions available");
        }
        // Randomly select a question from the list
        int randomIndex = (int) (Math.random() * questions.size());
        return questions.get(randomIndex);
    }


    @Transactional
    public QuestionOptionDTO createQuestion(QuestionOptionDTO dto) {
        boolean valid = dto.getOptions().stream()
                .anyMatch(opt -> opt.getLabel().equals(dto.getCorrectOption()));
        if (!valid) {
            throw new IllegalArgumentException("Correct option must match one of the labels");
        }

        Question question = mapToQuestionEntity(dto);
        Question saved = questionRepository.save(question);

        return mapToQuestionOptionDTO(saved);
    }

    public QuestionOptionDTO getQuestionById(UUID id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));
        return mapToQuestionOptionDTO(question);
    }

    public List<QuestionOptionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(this::mapToQuestionOptionDTO)
                .toList();
    }


    private QuestionOptionDTO mapToQuestionOptionDTO(Question question) {
        return QuestionOptionDTO.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .correctOption(question.getCorrectOption())
                .options(
                        question.getOptions().stream()
                                .map(opt -> QuestionOptionDTO.OptionDTO.builder()
                                        .id(opt.getId())
                                        .label(opt.getLabel())
                                        .text(opt.getText())
                                        .build())
                                .toList()
                )
                .build();
    }

    private Question mapToQuestionEntity(QuestionOptionDTO dto) {
        Question question = Question.builder()
                .questionText(dto.getQuestionText())
                .correctOption(dto.getCorrectOption())
                .build();

        List<Option> options = dto.getOptions().stream()
                .map(opt -> Option.builder()
                        .label(opt.getLabel())
                        .text(opt.getText())
                        .question(question)
                        .build())
                .toList();

        question.setOptions(options);
        return question;
    }

}
