package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ToeicVocabWordAudioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String voice;

    @ManyToOne
    @JoinColumn
    private ToeicStorageEntity audioStorage;

    public ToeicVocabWordAudioEntity() {

    }
}
