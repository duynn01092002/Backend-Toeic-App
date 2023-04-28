package com.hcmute.backendtoeicapp.dto.toeicvocabtopic;

import com.hcmute.backendtoeicapp.entities.ToeicVocabWordAudioEntity;
import com.hcmute.backendtoeicapp.entities.ToeicVocabWordEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicVocabWordResponse {
    private Integer id;

    private String english;

    private String vietnamese;

    private String pronounce;

    private String exampleVietnamese;

    private String exampleEnglish;

    private String imageUrl;

    private List<ToeicVocabWordAudioResponse> audio;

    public ToeicVocabWordResponse(
            ToeicVocabWordEntity entity
    ) {
        this.setId(entity.getId());
        this.setEnglish(entity.getEnglish());
        this.setVietnamese(entity.getVietnamese());
        this.setPronounce(entity.getPronounce());
        this.setExampleEnglish(entity.getExampleEnglish());
        this.setExampleVietnamese(entity.getExampleVietnamese());

        if (entity.getWordImage() != null) {
            this.imageUrl = "/api/toeic/toeic-storage/view/" + entity.getWordImage().getId();
        }
    }

    public ToeicVocabWordResponse(
            ToeicVocabWordEntity entity,
            List<ToeicVocabWordAudioEntity> audioEntities
    ) {
        this(entity);
        this.audio = audioEntities
                .stream()
                .map(ToeicVocabWordAudioResponse::new)
                .toList();
    }
}
