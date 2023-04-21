package com.hcmute.backendtoeicapp.dto.toeicAnswerChoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateToeicAnswerChoiceRequest {
    @JsonIgnore
    private Integer id;
    private String label;
    private String content;
    private String explain;
    private Integer toeicQuestionId;
}
