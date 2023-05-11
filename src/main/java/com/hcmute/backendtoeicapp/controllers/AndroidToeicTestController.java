package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.repositories.ToeicFullTestRepository;
import com.hcmute.backendtoeicapp.services.interfaces.AndroidToeicTestService;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicSystemVocabularyService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/android/toeic-test")
public class AndroidToeicTestController {
    private final AndroidToeicTestService androidToeicTestService;
    private final ToeicSystemVocabularyService toeicSystemVocabularyService;

    public AndroidToeicTestController(
            AndroidToeicTestService androidToeicTestService,
            ToeicSystemVocabularyService toeicSystemVocabularyService
    ) {
        this.androidToeicTestService = androidToeicTestService;
        this.toeicSystemVocabularyService = toeicSystemVocabularyService;
    }

    @GetMapping("get-tests-data")
    public BaseResponse getAllTestsDataJson() {
        return this.androidToeicTestService.getAllTestsData();
    }

    @GetMapping("get-vocabs-data-zip")
    public ResponseEntity getVocabsDataZip() throws IOException {
        final byte[] stream = this.toeicSystemVocabularyService.downloadBackupZip();
        InputStreamSource streamSource = new InputStreamResource(new ByteArrayInputStream(stream));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dataset.zip")
                .contentLength(stream.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(streamSource);
    }
}
