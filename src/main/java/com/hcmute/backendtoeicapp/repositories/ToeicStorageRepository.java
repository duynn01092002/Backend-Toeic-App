package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToeicStorageRepository extends JpaRepository<ToeicStorageEntity, Integer> {
    @Query("SELECT u FROM ToeicStorageEntity u WHERE u.id=:id")
    ToeicStorageEntity getById(@Param("id") Integer id);
}
