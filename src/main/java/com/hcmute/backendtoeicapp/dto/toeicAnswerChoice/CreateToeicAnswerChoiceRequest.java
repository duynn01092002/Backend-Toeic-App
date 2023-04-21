package com.hcmute.backendtoeicapp.dto.toeicAnswerChoice;

import lombok.Data;

@Data
public class CreateToeicAnswerChoiceRequest {
    private String label;
    private String content;
    private String explain;
    private Integer toeicQuestionId;
}
