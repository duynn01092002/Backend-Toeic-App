package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_toeicFullTest")
public class ToeicFullTestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="fullName")
    private String fullName;
    @Column(name = "slug", unique = true)
    private String slug;
    @Column(name = "checkSum")
    private String checkSum;
    public ToeicFullTestEntity() {}

}
