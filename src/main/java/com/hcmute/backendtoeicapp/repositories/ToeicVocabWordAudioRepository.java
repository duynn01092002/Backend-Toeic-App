package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicVocabWordAudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToeicVocabWordAudioRepository extends JpaRepository<ToeicVocabWordAudioEntity, Integer> {
    @Query("SELECT u FROM ToeicVocabWordAudioEntity u WHERE u.word.id = :wordId")
    List<ToeicVocabWordAudioEntity> getListAudioByWordId(
            @Param("wordId") Integer wordId
    );
}
