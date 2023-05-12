package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name="tbl_toeicQuestionGroup")
public class ToeicQuestionGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="toeic_part_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicPartEntity toeicPartEntity;

    public ToeicQuestionGroupEntity() {}
}
