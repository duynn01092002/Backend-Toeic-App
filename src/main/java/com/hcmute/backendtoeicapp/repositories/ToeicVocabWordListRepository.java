package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicVocabWordListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToeicVocabWordListRepository extends JpaRepository<ToeicVocabWordListEntity, Integer> {
}
