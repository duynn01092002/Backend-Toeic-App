package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicPart.UpdateToeicPartRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestionGroup.CreateToeicQuestionGroupRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestionGroup.UpdateToeicQuestionGroupRequest;

public interface ToeicQuestionGroupService {
    BaseResponse createToeicQuestionGroup(CreateToeicQuestionGroupRequest request);
    BaseResponse getAllToeicQuestiongroup();
    BaseResponse getToeicQuestionGroupById(Integer id);
    BaseResponse getToeicQuestionGroupsByPartId(Integer id);
    BaseResponse updateToeicQuestionById(Integer id, UpdateToeicQuestionGroupRequest request);
    BaseResponse deleteToeicQuestionById(Integer id);
}
