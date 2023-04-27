package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ToeicVocabWordListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private ToeicStorageEntity wordImage;

    private String english;

    private String vietnamese;

    private String pronounce;

    private String exampleEnglish;

    private String exampleVietnamese;

    public ToeicVocabWordListEntity() {

    }
}
