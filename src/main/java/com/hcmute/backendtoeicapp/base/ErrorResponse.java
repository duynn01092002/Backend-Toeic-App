package com.hcmute.backendtoeicapp.base;

public class ErrorResponse extends BaseResponse{
    @Override
    public Boolean getIsSuccess() {
        return false;
    }
}
