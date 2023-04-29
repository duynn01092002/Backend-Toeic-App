package com.hcmute.backendtoeicapp.dto.toeicQuestion;

import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.ToeicAnswerChoiceResponse;
import com.hcmute.backendtoeicapp.entities.ToeicAnswerChoiceEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import lombok.Data;

import java.util.List;

@Data
public class ToeicQuestionResponse {
    private Integer id;
    private Integer questionNumber;
    private String correctAnswer;
    private String content;

    private List<ToeicAnswerChoiceResponse> choices;

    public ToeicQuestionResponse() {

    }

    public ToeicQuestionResponse(ToeicQuestionEntity entity) {
        this.id = entity.getId();
        this.questionNumber = entity.getQuestionNumber();
        this.content = entity.getContent();
        this.correctAnswer = entity.getCorrectAnswer();
    }

    public ToeicQuestionResponse(ToeicQuestionEntity entity, List<ToeicAnswerChoiceEntity> choiceEntities) {
        this(entity);
        this.choices = choiceEntities.stream().map(ToeicAnswerChoiceResponse::new).toList();
    }
}
