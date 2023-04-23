package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/backup-service")
public class ToeicBackupServiceController {
    @Autowired
    public ToeicBackupService toeicBackupService;

    @PostMapping("restore-toeic-test")
    @Transactional
    public BaseResponse restoreToeicTest(
            @RequestParam("file")MultipartFile file ) {
        try {
            return this.toeicBackupService.restoreToeicTest(file);
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
