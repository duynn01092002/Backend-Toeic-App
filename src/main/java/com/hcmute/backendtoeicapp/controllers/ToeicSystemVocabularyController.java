package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.CreateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.services.ToeicSystemVocabularyServiceImpl;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicSystemVocabularyService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/toeic/toeic-system-vocabulary")
@CrossOrigin("*")
public class ToeicSystemVocabularyController {
    @Autowired
    private ToeicSystemVocabularyService toeicSystemVocabularyService;

    public ToeicSystemVocabularyController() {

    }

    @PostMapping("topic")
    public BaseResponse createTopic(
            @RequestParam(value = "topicName") String topicName,
            @RequestParam(value = "image", required = false) MultipartFile uploadedTopicImage
            ) {
        CreateToeicVocabTopicRequest request = new CreateToeicVocabTopicRequest(
                topicName, uploadedTopicImage
        );
        return this.toeicSystemVocabularyService.createTopic(request);
    }

    @GetMapping("topic")
    public BaseResponse listAllTopics() {
        return this.toeicSystemVocabularyService.listAllTopics();
    }
}
