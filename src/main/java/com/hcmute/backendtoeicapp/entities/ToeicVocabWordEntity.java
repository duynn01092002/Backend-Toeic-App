package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class ToeicVocabWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicStorageEntity audioStorage;

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicVocabTopicEntity topic;

    public ToeicVocabWordEntity() {

    }
}
