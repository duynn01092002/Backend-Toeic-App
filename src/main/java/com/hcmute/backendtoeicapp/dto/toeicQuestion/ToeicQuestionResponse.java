package com.hcmute.backendtoeicapp.dto.toeicQuestion;

import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import lombok.Data;

@Data
public class ToeicQuestionResponse {
    private Integer id;
    private Integer questionNumber;
    private String correctAnswer;
    private String content;

    public ToeicQuestionResponse() {

    }

    public ToeicQuestionResponse(ToeicQuestionEntity entity) {
        this.id = entity.getId();
        this.questionNumber = entity.getQuestionNumber();
        this.content = entity.getContent();
        this.correctAnswer = entity.getCorrectAnswer();
    }
}
