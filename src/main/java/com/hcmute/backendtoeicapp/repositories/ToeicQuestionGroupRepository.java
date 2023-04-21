package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicFullTestEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToeicQuestionGroupRepository extends JpaRepository<ToeicQuestionGroupEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicQuestionGroupEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);
    @Query("SELECT u FROM ToeicQuestionGroupEntity u WHERE u.id = :id")
    ToeicQuestionGroupEntity getById(@Param("id") Integer id);
}
