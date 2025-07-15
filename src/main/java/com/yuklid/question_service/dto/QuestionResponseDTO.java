package com.yuklid.question_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {
    @NotBlank
    private UUID id;
    @NotBlank
    private String questionText;
    @NotEmpty
    private List<QuestionResponseDTO.OptionDTO> options;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionDTO {
        @NotBlank
        private UUID id;
        @NotBlank
        private String text;
//        @NotBlank
//        private Boolean isCorrect;
    }
}
