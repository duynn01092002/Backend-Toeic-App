package com.hcmute.backendtoeicapp.services.toeicbackup.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class ToeicFullTestBackupModel {
    private String fullName;
    private String slug;

    private List<ToeicPartBackupModel> parts;

    public ToeicFullTestBackupModel(){}
}
