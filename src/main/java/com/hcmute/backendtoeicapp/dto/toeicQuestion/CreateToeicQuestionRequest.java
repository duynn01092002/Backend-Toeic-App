package com.hcmute.backendtoeicapp.dto.toeicQuestion;

import lombok.Data;

@Data
public class CreateToeicQuestionRequest {
    private Integer questionNumber;
    private Integer toeicQuestionGroupId;
}
