package com.hcmute.backendtoeicapp.dto.android.tests;

import com.hcmute.backendtoeicapp.entities.ToeicItemContentEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AndroidToeicQuestionGroup {
    private Integer serverId;

    private List<AndroidToeicQuestion> questions;

    private List<AndroidItemContent> questionContents;

    private List<AndroidItemContent> transcriptContents;

    public AndroidToeicQuestionGroup(ToeicQuestionGroupEntity entity) {
        this.serverId = entity.getId();
    }
}
