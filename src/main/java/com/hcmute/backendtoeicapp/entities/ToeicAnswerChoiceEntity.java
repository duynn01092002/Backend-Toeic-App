package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_toeicAnswerChoice")
public class ToeicAnswerChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="c_label")
    private String label;
    private String content;
    @Column(name="c_explain")
    private String explain;
    @ManyToOne
    @JoinColumn(name = "toeic_question_id")
    private ToeicQuestionEntity toeicQuestionEntity;

    public ToeicAnswerChoiceEntity() {}
}
