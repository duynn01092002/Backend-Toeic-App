package com.hcmute.backendtoeicapp.dto.android.vocab;

import com.hcmute.backendtoeicapp.entities.ToeicVocabTopicEntity;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AndroidToeicVocabTopic {
    private Integer serverId;

    private String topicName;

    private String imageFileName;

    private List<AndroidToeicVocabWord> words;

    public AndroidToeicVocabTopic(
            @NonNull ToeicVocabTopicEntity entity
    ) {
        this.serverId = entity.getId();
        this.topicName = entity.getTopicName();
    }
}
