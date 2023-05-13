package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicUser.ToeicUserSignupRequest;

public interface ToeicUserService {
    BaseResponse userSignup(ToeicUserSignupRequest request);
}
