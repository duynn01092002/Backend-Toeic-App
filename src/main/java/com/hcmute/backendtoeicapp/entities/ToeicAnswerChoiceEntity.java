package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicQuestionEntity toeicQuestionEntity;

    public ToeicAnswerChoiceEntity() {}
}
