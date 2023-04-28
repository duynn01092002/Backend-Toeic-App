package com.hcmute.backendtoeicapp.services.vocabbackup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicVocabTopicBackupModel {
    private String topicName;
    private String img;

    private List<ToeicVocabWordBackupModel> wordList;
}
