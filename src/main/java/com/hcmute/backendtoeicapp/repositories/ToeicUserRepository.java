package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToeicUserRepository extends JpaRepository<ToeicUserEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ToeicUserEntity u WHERE u.gmail=:gmail")
    boolean existsByGmail(@Param("gmail") String gmail);

    @Query("SELECT u FROM ToeicUserEntity u WHERE u.gmail=:gmail")
    ToeicUserEntity getByGmail(@Param("gmail") String gmail);
}
