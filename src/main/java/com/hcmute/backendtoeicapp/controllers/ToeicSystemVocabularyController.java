package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.*;
import com.hcmute.backendtoeicapp.services.ToeicSystemVocabularyServiceImpl;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicSystemVocabularyService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BaseMultiResolutionImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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

    @PostMapping("word")
    public BaseResponse createWord(
            @RequestBody CreateToeicWordRequest request
    ) {
        return this.toeicSystemVocabularyService.createWord(request);
    }

    @GetMapping("word/list-by-topic/{id}")
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

    @PostMapping("word/{id}/add-audio")
    public BaseResponse addWordAudio(
            @PathVariable("id") Integer wordId,
            @RequestParam("file") MultipartFile uploadedAudioFile,
            @RequestParam("voice") String voice
    ) {
        AddWordAudioRequest request = new AddWordAudioRequest();
        request.setWordId(wordId);
        request.setUploadedAudio(uploadedAudioFile);
        request.setVoice(voice);
        return this.toeicSystemVocabularyService.addWordAudio(request);
    }

    @PutMapping("word/{id}")
    public BaseResponse updateWordInformation(
            @PathVariable("id") Integer wordId,
            @RequestBody UpdateWordInformationRequest request
    ) {
        request.setWordId(wordId);
        return this.toeicSystemVocabularyService.updateWordInformation(request);
    }

    @DeleteMapping("word/{id}")
    @Transactional
    public BaseResponse deleteWordById(
            @PathVariable("id") Integer wordId
    ) {
        return this.toeicSystemVocabularyService.deleteWordById(wordId);
    }

    @DeleteMapping("audio/{id}")
    public BaseResponse deleteWordAudioById(
            @PathVariable("id") Integer audioId
    ) {
        return this.toeicSystemVocabularyService.deleteWordAudioById(audioId);
    }

    @GetMapping("download-backup")
    public ResponseEntity downloadBackup() throws IOException {
        final byte[] stream = this.toeicSystemVocabularyService.downloadBackupZip();
        InputStreamSource streamSource = new InputStreamResource(new ByteArrayInputStream(stream));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dataset.zip")
                .contentLength(stream.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(streamSource);
    }

    @Deprecated
    @PostMapping("restore-full-database")
    @Transactional
    public BaseResponse restoreFullVocabDatabase(
            @RequestParam(value = "file") MultipartFile uploadedBackupFile
    ) {
        return this.toeicSystemVocabularyService.restoreToeicBackupZipFile(uploadedBackupFile);
    }
}
