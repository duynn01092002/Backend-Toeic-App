package com.hcmute.backendtoeicapp.services.toeicbackup.model;

import com.hcmute.backendtoeicapp.entities.ToeicFullTestEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ToeicPartBackupModel {
    private Integer partNumber;
    private List<ToeicQuestionGroupBackupModel> groups;

    public ToeicPartBackupModel(){}
}
