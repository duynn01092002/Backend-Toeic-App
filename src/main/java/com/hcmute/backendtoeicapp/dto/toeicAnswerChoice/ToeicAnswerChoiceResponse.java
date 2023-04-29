package com.hcmute.backendtoeicapp.dto.toeicAnswerChoice;

import com.hcmute.backendtoeicapp.entities.ToeicAnswerChoiceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicAnswerChoiceResponse {
    private String label;

    private String content;

    private String explain;

    public ToeicAnswerChoiceResponse(ToeicAnswerChoiceEntity entity) {
        this.label = entity.getLabel();
        this.content = entity.getContent();
        this.explain = entity.getExplain();
    }
}
