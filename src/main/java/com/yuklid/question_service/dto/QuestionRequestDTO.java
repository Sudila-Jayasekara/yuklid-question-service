package com.yuklid.question_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO { //create question request DTO
    @NotBlank
    private String questionText;
    @NotEmpty
    private List<QuestionRequestDTO.OptionDTO> options;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionDTO {
        @NotBlank
        private Boolean isCorrect;
        @NotBlank
        private String text;
    }
}
