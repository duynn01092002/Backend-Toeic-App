package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ToeicUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String gmail;
    private String fullName;

    public ToeicUserEntity() {}
}
