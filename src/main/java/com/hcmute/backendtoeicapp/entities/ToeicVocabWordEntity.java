package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ToeicVocabWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private ToeicStorageEntity wordImage;

    private String english;

    @Column(columnDefinition = "TEXT")
    private String vietnamese;

    private String pronounce;

    @Column(columnDefinition = "TEXT")
    private String exampleEnglish;

    @Column(columnDefinition = "TEXT")
    private String exampleVietnamese;

    @ManyToOne
    @JoinColumn
    private ToeicVocabTopicEntity topic;

    public ToeicVocabWordEntity() {

    }
}
