package com.hcmute.backendtoeicapp.dto.toeicQuestionGroup;

import com.hcmute.backendtoeicapp.dto.toeicItemContent.ToeicItemContentResponse;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.ToeicQuestionResponse;
import com.hcmute.backendtoeicapp.entities.ToeicItemContentEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ToeicQuestionGroupResponse {
    private Integer id;
    private List<ToeicQuestionResponse> questions;
    private List<ToeicItemContentResponse> questionContents;
    private List<ToeicItemContentResponse> transcripts;

    public ToeicQuestionGroupResponse() {
        this.questions = new ArrayList<>();
        this.questionContents = new ArrayList<>();
        this.transcripts = new ArrayList<>();
    }
}
