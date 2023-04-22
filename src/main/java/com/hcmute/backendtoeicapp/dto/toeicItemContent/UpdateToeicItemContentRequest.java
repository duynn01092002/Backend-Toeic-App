package com.hcmute.backendtoeicapp.dto.toeicItemContent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateToeicItemContentRequest {
    @JsonIgnore
    private Integer id;
    private String contentType;
    private String content;
    private Integer storageId;
    private Integer questionContentId;
    private Integer questionTranscriptId;
}
