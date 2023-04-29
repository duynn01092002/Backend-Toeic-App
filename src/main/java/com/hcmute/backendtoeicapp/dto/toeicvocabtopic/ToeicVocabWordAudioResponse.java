package com.hcmute.backendtoeicapp.dto.toeicvocabtopic;

import com.hcmute.backendtoeicapp.entities.ToeicVocabWordAudioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicVocabWordAudioResponse {
    private Integer audioId;

    private String voice;

    private String url;

    public ToeicVocabWordAudioResponse(ToeicVocabWordAudioEntity entity) {
        this.setAudioId(entity.getId());
        this.setVoice(entity.getVoice());
        this.setUrl("/api/toeic/toeic-storage/view/" + entity.getAudioStorage().getId());
    }
}
