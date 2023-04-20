package com.hcmute.backendtoeicapp.dto.toeicPart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateToeicPartRequest {
    private Integer partNumber;
    private Integer toeicFullTest;

}
