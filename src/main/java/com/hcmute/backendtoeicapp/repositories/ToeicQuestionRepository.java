package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToeicQuestionRepository extends JpaRepository<ToeicQuestionEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicQuestionEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicQuestionEntity u WHERE u.id=:id")
    ToeicQuestionEntity getById(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicQuestionEntity u WHERE u.toeicQuestionGroupEntity.id = :groupId")
    List<ToeicQuestionEntity> getToeicQuestionEntitiesByToeicQuestionGroup(
            @Param("groupId") Integer groupId
    );

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicQuestionEntity u WHERE " +
            "u.toeicQuestionGroupEntity.toeicPartEntity.toeicFullTestEntity.id=:id")
    boolean existsByTestId(@Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicQuestionEntity u " +
            "INNER JOIN ToeicQuestionGroupEntity v ON u.toeicQuestionGroupEntity.id=v.id WHERE " +
            "u.questionNumber=:questionNumber AND u.toeicQuestionGroupEntity.id=:questionGroupId")
    boolean existsByQuestionNumberAndGroupId(@Param("questionNumber") Integer questionNumber,
                                             @Param("questionGroupId") Integer questionGroupId);

    @Query("SELECT u FROM ToeicQuestionEntity u INNER JOIN ToeicAnswerChoiceEntity v " +
            "ON u.id=v.toeicQuestionEntity.id WHERE v.id=:id")
    ToeicQuestionEntity getToeicQuestionEntityByAnswerChoiceId(@Param("id") Integer id);
}
