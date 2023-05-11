package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicFullTestEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToeicQuestionGroupRepository extends JpaRepository<ToeicQuestionGroupEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicQuestionGroupEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);
    @Query("SELECT u FROM ToeicQuestionGroupEntity u WHERE u.id = :id")
    ToeicQuestionGroupEntity getById(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicQuestionGroupEntity u WHERE u.toeicPartEntity.id = :id")
    List<ToeicQuestionGroupEntity> getToeicQuestionGroupEntitiesByToeicPartEntity(
            @Param("id") Integer id
    );
    @Query("SELECT u FROM ToeicQuestionGroupEntity u INNER JOIN ToeicQuestionEntity v " +
            "ON u.id=v.toeicQuestionGroupEntity.id WHERE v.id=:id")
    ToeicQuestionGroupEntity getToeicQuestionGroupEntityByQuestionId(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicQuestionGroupEntity u INNER JOIN ToeicItemContentEntity v " +
            "ON u.id=v.toeicQuestionGroupEntityQuestionContent.id WHERE v.id=:id")
    ToeicQuestionGroupEntity getToeicQuestionGroupEntityByQuestionContentId(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicQuestionGroupEntity u INNER JOIN ToeicItemContentEntity v " +
            "ON u.id=v.toeicQuestionGroupEntityTranscript.id WHERE v.id=:id")
    ToeicQuestionGroupEntity getToeicQuestionGroupEntityByTranscriptId(@Param("id") Integer id);
}
