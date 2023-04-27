package com.hcmute.backendtoeicapp.dto.toeicvocabtopic;

import com.hcmute.backendtoeicapp.entities.ToeicVocabTopicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicVocabTopicResponse {
    private Integer topicId;

    private String topicName;

    private String topicImageUrl;

    public ToeicVocabTopicResponse(ToeicVocabTopicEntity entity) {
        assert entity != null;
        this.topicId = entity.getId();
        this.topicName = entity.getTopicName();

        if (entity.getToeicTopicImage() != null) {
            this.topicImageUrl = "/api/toeic/toeic-storage/view/" + entity.getToeicTopicImage().getId();
        }
        else {
            this.topicImageUrl = "/static/default-topic-image.png";
        }
    }
}
