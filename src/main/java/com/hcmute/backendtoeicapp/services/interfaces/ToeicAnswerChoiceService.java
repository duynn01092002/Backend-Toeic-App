package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.CreateToeicAnswerChoiceRequest;
import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.UpdateToeicAnswerChoiceRequest;

public interface ToeicAnswerChoiceService {
    BaseResponse getToeicAnswerChoiceById(Integer id);

    BaseResponse getAllToeicAnswerChoices();

    BaseResponse createToeicAnswerChoice(CreateToeicAnswerChoiceRequest request);

    BaseResponse updateToeicAnswerChoice(UpdateToeicAnswerChoiceRequest request);

    BaseResponse deleteToeicAnswerChoiceEntity(Integer id);
}
