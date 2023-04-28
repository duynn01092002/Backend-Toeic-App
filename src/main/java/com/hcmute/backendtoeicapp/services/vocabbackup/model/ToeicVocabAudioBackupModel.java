package com.hcmute.backendtoeicapp.services.vocabbackup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicVocabAudioBackupModel {
    private String speaker;
    private String file;
}
