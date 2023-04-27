package com.hcmute.backendtoeicapp.dto.toeicvocabtopic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateToeicVocabTopicRequest {
    @JsonIgnore
    private Integer topicId;

    private String topicName;

    private MultipartFile uploadedTopicImage;
}
