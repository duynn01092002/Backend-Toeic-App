package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.FavoriteVocabGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteVocabGroupRepository extends JpaRepository<FavoriteVocabGroupEntity, Integer> {
    @Query("SELECT u FROM FavoriteVocabGroupEntity u WHERE u.toeicUserEntity.gmail=:gmail")
    List<FavoriteVocabGroupEntity> getVocabGroupsByGmail(@Param("gmail") String gmail);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM FavoriteVocabGroupEntity u " +
            "INNER JOIN ToeicUserEntity v ON u.toeicUserEntity.id=v.id WHERE " +
            "u.groupName=:groupName AND u.toeicUserEntity.gmail=:gmail")
    boolean existsByGroupNameAndGmail(@Param("groupName") String groupName,
                                      @Param("gmail") String gmail);
}
