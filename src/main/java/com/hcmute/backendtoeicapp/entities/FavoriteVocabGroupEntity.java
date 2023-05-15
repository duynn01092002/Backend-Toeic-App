package com.hcmute.backendtoeicapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class FavoriteVocabGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String groupName;
    @ManyToOne
    @JoinColumn(name = "toeic_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToeicUserEntity toeicUserEntity;
    public FavoriteVocabGroupEntity() {}
}
