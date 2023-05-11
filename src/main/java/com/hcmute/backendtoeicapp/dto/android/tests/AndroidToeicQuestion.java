package com.hcmute.backendtoeicapp.dto.android.tests;

import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AndroidToeicQuestion {
    private Integer serverId;

    private Integer questionNumber;

    private String correctAnswer;

    private String content;

    private List<AndroidAnswerChoice> choices;

    public AndroidToeicQuestion(ToeicQuestionEntity entity) {
        this.serverId = entity.getId();
        this.questionNumber = entity.getQuestionNumber();
        this.correctAnswer = entity.getCorrectAnswer();
        this.content = entity.getContent();
    }
}
