package com.hcmute.backendtoeicapp.dto.toeicQuestion;

import lombok.Data;

@Data
public class CreateToeicQuestionRequest {
    private Integer questionNumber;
    private String correctAnswer;
    private Integer toeicQuestionGroupId;
}
