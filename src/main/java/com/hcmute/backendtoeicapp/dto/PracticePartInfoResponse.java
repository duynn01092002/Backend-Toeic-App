package com.hcmute.backendtoeicapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticePartInfoResponse {
    private Integer partId;
    private String name;
    private Integer numOfQuestions;
}
