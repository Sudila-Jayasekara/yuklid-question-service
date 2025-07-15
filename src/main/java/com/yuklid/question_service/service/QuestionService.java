package com.yuklid.question_service.service;

import com.yuklid.question_service.dto.QuestionRequestDTO;
import com.yuklid.question_service.dto.QuestionResponseDTO;
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

    @Transactional
    public QuestionResponseDTO createQuestion(QuestionRequestDTO dto) {
        Question question = mapToQuestionEntity(dto);
        Question saved = questionRepository.save(question);
        return mapToQuestionOptionDTO(saved);
    }

    public QuestionResponseDTO getQuestionById(UUID id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));
        return mapToQuestionOptionDTO(question);
    }

    public QuestionResponseDTO getRandomQuestion() {
        Question randomQuestion = questionRepository.findRandomQuestion();
        if(randomQuestion == null){
            throw new QuestionNotFoundException("No questions available");
        }
        return mapToQuestionOptionDTO(randomQuestion);
    }

    private QuestionResponseDTO mapToQuestionOptionDTO(Question question) {
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .options(
                        question.getOptions().stream()
                                .map(opt -> QuestionResponseDTO.OptionDTO.builder()
                                        .id(opt.getId())
                                        .text(opt.getText())
                                        .build())
                                .toList()
                )
                .build();
    }

    private Question mapToQuestionEntity(QuestionRequestDTO dto) {
        Question question = Question.builder()
                .questionText(dto.getQuestionText())
                .build();

        List<Option> options = dto.getOptions().stream()
                .map(opt -> Option.builder()
                        .text(opt.getText())
                        .isCorrect(opt.getIsCorrect())
                        .question(question)
                        .build())
                .toList();

        question.setOptions(options);
        return question;
    }


}
