package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name="tbl_toeicPart")
public class ToeicPartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer partNumber;
    @ManyToOne
    @JoinColumn(name="toeic_full_test_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicFullTestEntity toeicFullTestEntity;

    public ToeicPartEntity() {}
}
