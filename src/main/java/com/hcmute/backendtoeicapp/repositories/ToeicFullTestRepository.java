package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicFullTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToeicFullTestRepository extends JpaRepository<ToeicFullTestEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicFullTestEntity u WHERE u.id=:id")
    boolean existsById(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicFullTestEntity u WHERE u.id = :id")
    ToeicFullTestEntity getById(@Param("id") Integer id);

    @Query("SELECT u FROM ToeicFullTestEntity u WHERE u.slug = :slug")
    ToeicFullTestEntity getToeicFullTestBySlug(
            @Param("slug") String slug);

    @Query("SELECT u FROM ToeicFullTestEntity u INNER JOIN ToeicPartEntity v ON u.id=v.toeicFullTestEntity.id " +
            "WHERE v.id=:id")
    ToeicFullTestEntity getToeicFullTestEntityByToeicPartId(@Param("id") Integer id);
}
