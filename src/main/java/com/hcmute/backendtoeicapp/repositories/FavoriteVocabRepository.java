package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.FavoriteVocabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteVocabRepository extends JpaRepository<FavoriteVocabEntity, Integer> {
    @Query("SELECT u FROM FavoriteVocabEntity u WHERE u.favoriteVocabGroupEntity.id=:id")
    List<FavoriteVocabEntity> getAllFavoriteVocabByGroupId(@Param("id") Integer id);
}
