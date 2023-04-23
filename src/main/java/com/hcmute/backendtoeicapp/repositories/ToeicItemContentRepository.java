package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicItemContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToeicItemContentRepository extends JpaRepository<ToeicItemContentEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicItemContentEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicItemContentEntity u WHERE u.id=:id")
    ToeicItemContentEntity getById(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicItemContentEntity u WHERE u.toeicQuestionGroupEntityQuestionContent.id = :id")
    List<ToeicItemContentEntity> getListQuestionContentByQuestionGroupId(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicItemContentEntity u WHERE u.toeicQuestionGroupEntityTranscript.id = :id")
    List<ToeicItemContentEntity> getListTranscriptByQuestionGroupId(@Param("id") Integer id);
}
