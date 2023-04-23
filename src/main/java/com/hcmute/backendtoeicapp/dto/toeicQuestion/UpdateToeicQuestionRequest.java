package com.hcmute.backendtoeicapp.dto.toeicQuestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateToeicQuestionRequest {
    @JsonIgnore
    private Integer id;
    private Integer questionNumber;
    private String correctAnswer;
    private Integer toeicQuestionGroupId;
}
