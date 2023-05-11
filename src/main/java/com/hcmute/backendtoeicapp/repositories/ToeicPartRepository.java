package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToeicPartRepository extends JpaRepository<ToeicPartEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicPartEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);
    @Query("SELECT u FROM ToeicPartEntity u WHERE u.id = :id")
    ToeicPartEntity getById(@Param("id") Integer id);
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM ToeicPartEntity p WHERE p.partNumber=:partNumber AND p.toeicFullTestEntity.id=:id")
    boolean existPartInFullTest(@Param("partNumber") Integer partNumber, @Param("id") Integer id);
    @Query("SELECT u FROM ToeicPartEntity u WHERE u.toeicFullTestEntity.id=:id")
    List<ToeicPartEntity> getToeicPartEntitiesByToeicFullTestId(@Param("id") Integer id);
    @Query("SELECT u FROM ToeicPartEntity u INNER JOIN ToeicQuestionGroupEntity v " +
            "ON u.id=v.toeicPartEntity.id WHERE v.id=:id")
    ToeicPartEntity getToeicPartEntityByQuestionGroupId(@Param("id") Integer id);
}
