package com.hcmute.backendtoeicapp.dto.toeicvocabtopic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateToeicVocabTopicRequest {
    private String topicName;

    private MultipartFile uploadedTopicImage;
}
