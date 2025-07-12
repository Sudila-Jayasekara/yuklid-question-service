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
public class QuestionOptionDTO {
    private UUID id;

    @NotBlank
    private String questionText;

    @NotBlank
    private String correctOption;

    @NotEmpty
    private List<OptionDTO> options;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionDTO {
        private UUID id;

        @NotBlank
        private String label;

        @NotBlank
        private String text;
    }

}
