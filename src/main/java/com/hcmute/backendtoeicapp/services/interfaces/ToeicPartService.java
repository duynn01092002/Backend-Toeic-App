package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicPart.CreateToeicPartRequest;
import com.hcmute.backendtoeicapp.dto.toeicPart.UpdateToeicPartRequest;

public interface ToeicPartService {
    BaseResponse createToeicPart(CreateToeicPartRequest request);

    BaseResponse updateToeicPartById(Integer id, UpdateToeicPartRequest request);

    BaseResponse getToeicPartById(Integer id);

    BaseResponse getAllToeicPart();

    BaseResponse deleteToeicPartById(Integer id);

    BaseResponse getToeicPartByToeicFullTestId(Integer id);
}
