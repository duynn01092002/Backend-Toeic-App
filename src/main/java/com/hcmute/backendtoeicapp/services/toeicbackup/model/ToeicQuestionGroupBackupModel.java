package com.hcmute.backendtoeicapp.services.toeicbackup.model;

import lombok.Data;

import java.util.List;

@Data
public class ToeicQuestionGroupBackupModel {
    private List<ToeicQuestionBackupModel> questions;
    private List<ToeicItemContentBackupModel> questionContents;
    private List<ToeicItemContentBackupModel> transcript;

    public ToeicQuestionGroupBackupModel() {}
}
