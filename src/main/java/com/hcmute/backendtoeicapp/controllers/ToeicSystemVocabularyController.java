package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.CreateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.UpdateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.services.ToeicSystemVocabularyServiceImpl;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicSystemVocabularyService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("topic/{id}")
    public BaseResponse listAllWordsByTopicId(
            @PathVariable("id") Integer topicId
    ) {
        return this.toeicSystemVocabularyService.listAllWordsByTopicId(topicId);
    }

    @GetMapping("word/{id}")
    public BaseResponse getWordDetailByWordId(
            @PathVariable("id") Integer wordId
    ) {
        return this.toeicSystemVocabularyService.getWordDetail(wordId);
    }

    @GetMapping("topic")
    public BaseResponse listAllTopics() {
        return this.toeicSystemVocabularyService.listAllTopics();
    }

    @PutMapping("topic/{id}")
    public BaseResponse updateTopic(
            @PathVariable("id") Integer topicId,
            @RequestParam(value = "topicName") String topicName,
            @RequestParam(value = "image", required = false) MultipartFile uploadedTopicImage
    ) {
        UpdateToeicVocabTopicRequest request = new UpdateToeicVocabTopicRequest(
                topicId, topicName, uploadedTopicImage
        );
        return this.toeicSystemVocabularyService.updateTopic(request);
    }

    @DeleteMapping("topic/{id}")
    public BaseResponse deleteTopic(
            @PathVariable("id") Integer topicId
    ) {
        return this.toeicSystemVocabularyService.deleteTopicById(topicId);
    }

    @PostMapping("restore-full-database")
    @Transactional
    public BaseResponse restoreFullVocabDatabase(
            @RequestParam(value = "file") MultipartFile uploadedBackupFile
    ) {
        return this.toeicSystemVocabularyService.restoreToeicBackupZipFile(uploadedBackupFile);
    }
}
