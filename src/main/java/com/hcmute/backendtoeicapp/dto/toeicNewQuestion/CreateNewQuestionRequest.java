package com.hcmute.backendtoeicapp.dto.toeicNewQuestion;

import com.hcmute.backendtoeicapp.entities.ToeicAnswerChoiceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateNewQuestionRequest {
    private Integer questionNumber;
    private String content;
    private List<ToeicAnswerChoiceEntity> toeicAnswers = new ArrayList<>();
    private String correctAnswer;
    private Integer toeicQuestionGroupId;
}
