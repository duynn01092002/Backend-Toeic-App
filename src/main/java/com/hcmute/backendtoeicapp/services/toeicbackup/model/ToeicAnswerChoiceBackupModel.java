package com.hcmute.backendtoeicapp.services.toeicbackup.model;

import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import lombok.Data;

@Data
public class ToeicAnswerChoiceBackupModel {
    private String label;
    private String content;
    private String explain;

    public ToeicAnswerChoiceBackupModel() {}
}
