package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(columnDefinition = "TEXT",name = "c_explain")
    private String explain;
    @ManyToOne
    @JoinColumn(name="toeic_question_group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicQuestionGroupEntity toeicQuestionGroupEntity;

    public ToeicQuestionEntity() {}
}
