package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicAnswerChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToeicAnswerChoiceRepository extends JpaRepository<ToeicAnswerChoiceEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicAnswerChoiceEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);
    @Query("SELECT u FROM ToeicAnswerChoiceEntity u WHERE u.id=:id")
    ToeicAnswerChoiceEntity getById(@Param("id") Integer id);
}
