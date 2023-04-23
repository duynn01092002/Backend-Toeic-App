package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_toeicQuestion")
public class ToeicQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer questionNumber;
    private String correctAnswer;
    private String content;
    @ManyToOne
    @JoinColumn(name="toeic_question_group_id")
    private ToeicQuestionGroupEntity toeicQuestionGroupEntity;

    public ToeicQuestionEntity() {}
}
