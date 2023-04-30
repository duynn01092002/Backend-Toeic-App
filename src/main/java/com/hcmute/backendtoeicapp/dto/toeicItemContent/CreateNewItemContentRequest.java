package com.hcmute.backendtoeicapp.dto.toeicItemContent;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateNewItemContentRequest {
    private String contentType;
    private String stringContent;
    private MultipartFile content;
    private Integer questionContentId;
    private Integer questionTranscriptId;
}
