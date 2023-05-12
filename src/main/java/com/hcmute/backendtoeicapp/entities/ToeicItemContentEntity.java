package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name="tbl_toeicItemContent")
public class ToeicItemContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contentType;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name="toeic_storage_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicStorageEntity toeicStorageEntity;
    @ManyToOne
    @JoinColumn(name="toeic_question_group_question_content_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicQuestionGroupEntity toeicQuestionGroupEntityQuestionContent;
    @ManyToOne
    @JoinColumn(name="toeic_question_group_transcript_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicQuestionGroupEntity toeicQuestionGroupEntityTranscript;

    public ToeicItemContentEntity() {}
}
