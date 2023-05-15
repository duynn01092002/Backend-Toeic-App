package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class FavoriteVocabEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String word;
    private String meaning;
    @ManyToOne
    @JoinColumn(name = "favorite_vocab_group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FavoriteVocabGroupEntity favoriteVocabGroupEntity;

    public FavoriteVocabEntity() {}
}
