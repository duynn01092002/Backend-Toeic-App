package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.CreateToeicItemContentRequest;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.UpdateToeicItemContentRequest;

public interface ToeicItemContentService {
    BaseResponse getAllToeicItemContents();

    BaseResponse getToeicItemContentById(Integer id);

    BaseResponse createToeicItemContent(CreateToeicItemContentRequest request) throws Exception;

    BaseResponse updateToeicItemContent(UpdateToeicItemContentRequest request) throws Exception;

    BaseResponse deleteToeicItemContent(Integer id);
}
