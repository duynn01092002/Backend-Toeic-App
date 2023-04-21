package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.CreateToeicQuestionRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.UpdateToeicQuestionRequest;

public interface ToeicQuestionService {
    BaseResponse getToeicQuestionById(Integer id);

    BaseResponse getAllToeicQuestions();

    BaseResponse createToeicQuestion(CreateToeicQuestionRequest request);

    BaseResponse updateToeicQuestion(UpdateToeicQuestionRequest request);

    BaseResponse deleteToeicQuestion(Integer id);
}
