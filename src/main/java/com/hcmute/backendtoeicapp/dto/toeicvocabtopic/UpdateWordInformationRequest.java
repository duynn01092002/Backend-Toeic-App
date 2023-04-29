package com.hcmute.backendtoeicapp.dto.toeicvocabtopic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWordInformationRequest {
    @JsonIgnore
    private Integer wordId;

    private String english;

    private String vietnamese;

    private String pronounce;

    private String exampleEnglish;

    private String exampleVietnamese;
}
