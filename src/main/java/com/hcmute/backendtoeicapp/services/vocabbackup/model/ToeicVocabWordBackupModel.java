package com.hcmute.backendtoeicapp.services.vocabbackup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicVocabWordBackupModel {
    private String img;
    private String eng;
    private String pronounce;
    private String viet;
    private String explain;
    private String exampleEng;
    private String exampleViet;

    private List<ToeicVocabAudioBackupModel> audio;
}
