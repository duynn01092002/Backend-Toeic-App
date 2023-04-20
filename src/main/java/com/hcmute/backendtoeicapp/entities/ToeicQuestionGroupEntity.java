package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_toeicQuestionGroup")
public class ToeicQuestionGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="toeic_part_id")
    private ToeicPartEntity toeicPartEntity;

    public ToeicQuestionGroupEntity() {}
}
