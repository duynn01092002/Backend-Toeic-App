package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToeicQuestionRepository extends JpaRepository<ToeicQuestionEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicQuestionEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);
    @Query("SELECT u FROM ToeicQuestionEntity u WHERE u.id=:id")
    ToeicQuestionEntity getById(@Param("id") Integer id);
}
