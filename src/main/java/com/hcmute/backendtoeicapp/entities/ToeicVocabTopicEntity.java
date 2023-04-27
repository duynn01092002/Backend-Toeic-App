package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ToeicVocabTopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String topicName;

    @ManyToOne
    @JoinColumn
    private ToeicStorageEntity toeicTopicImage;

    public ToeicVocabTopicEntity() {

    }
}
