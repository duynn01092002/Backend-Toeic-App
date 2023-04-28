package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicVocabWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToeicVocabWordRepository extends JpaRepository<ToeicVocabWordEntity, Integer> {
    @Query("SELECT u FROM ToeicVocabWordEntity u WHERE u.topic.id = :topicId")
    List<ToeicVocabWordEntity> getAllWordsByTopicId(
            @Param("topicId") Integer topicId
    );
}
