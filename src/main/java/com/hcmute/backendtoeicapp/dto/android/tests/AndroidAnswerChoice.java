package com.hcmute.backendtoeicapp.dto.android.tests;

import com.hcmute.backendtoeicapp.entities.ToeicAnswerChoiceEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AndroidAnswerChoice {
    private Integer serverId;

    private String label;

    private String content;

    private String explain;

    public AndroidAnswerChoice(ToeicAnswerChoiceEntity entity) {
        this.serverId = entity.getId();
        this.label = entity.getLabel();
        this.content = entity.getContent();
        this.explain = entity.getExplain();
    }
}
