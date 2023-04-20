package com.hcmute.backendtoeicapp.base;

import lombok.Data;

@Data
public class SuccessfulResponse extends BaseResponse{
    @Override
    public Boolean getIsSuccess() {
        return true;
    }
}
