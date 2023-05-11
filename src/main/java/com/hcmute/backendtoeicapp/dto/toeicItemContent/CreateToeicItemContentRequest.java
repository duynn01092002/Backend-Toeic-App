package com.hcmute.backendtoeicapp.dto.toeicItemContent;

import lombok.Data;

@Deprecated
@Data
public class CreateToeicItemContentRequest {
    private String contentType;
    private String content;
    private Integer storageId;
    private Integer questionContentId;
    private Integer questionTranscriptId;
}
