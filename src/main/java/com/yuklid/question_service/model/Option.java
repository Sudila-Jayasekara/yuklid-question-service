package com.yuklid.question_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String label; // "A", "B", etc.
    private String text;  // The actual answer

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
