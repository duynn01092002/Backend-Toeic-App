package com.hcmute.backendtoeicapp.base;

import lombok.Data;

@Data
public abstract class BaseResponse {
    private Boolean isSuccess;
    private String message;
    private Object data;
}
