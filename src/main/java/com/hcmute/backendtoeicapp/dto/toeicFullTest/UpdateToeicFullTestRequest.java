package com.hcmute.backendtoeicapp.dto.toeicFullTest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateToeicFullTestRequest {
    @JsonIgnore
    private Integer id;
    private String fullName;
}
