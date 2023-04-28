package com.hcmute.backendtoeicapp.repositories;

import com.hcmute.backendtoeicapp.entities.ToeicVocabWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToeicVocabWordRepository extends JpaRepository<ToeicVocabWordEntity, Integer> {
}
