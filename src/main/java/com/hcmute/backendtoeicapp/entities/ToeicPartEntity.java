package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

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
    private ToeicFullTestEntity toeicFullTestEntity;

    public ToeicPartEntity() {}
}
