package com.hcmute.backendtoeicapp.services.toeicbackup.model;

import com.hcmute.backendtoeicapp.entities.ToeicQuestionGroupEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ToeicQuestionBackupModel {
    private Integer questionNumber;
    private String question;
    private String correctAnswer;

    public List<ToeicAnswerChoiceBackupModel> choices;

    public ToeicQuestionBackupModel() {}
}
