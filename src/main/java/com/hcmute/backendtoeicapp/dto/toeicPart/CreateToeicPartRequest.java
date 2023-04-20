package com.hcmute.backendtoeicapp.dto.toeicPart;

import lombok.Data;

@Data
public class CreateToeicPartRequest {
    private Integer partNumber;
    private Integer toeicFullTest;
}
