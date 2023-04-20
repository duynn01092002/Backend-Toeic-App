package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicFullTest.CreateToeicFullTestRequest;
import com.hcmute.backendtoeicapp.dto.toeicFullTest.UpdateToeicFullTestRequest;

public interface ToeicFullTestService {
    BaseResponse createToeicFullTest(CreateToeicFullTestRequest request);

    BaseResponse findAllToeicFullTests();

    BaseResponse findToeicFullTestById(Integer id);

    BaseResponse updateToeicFullTestById(UpdateToeicFullTestRequest request);

    BaseResponse deleteToeicFullTestById(Integer id);
}
