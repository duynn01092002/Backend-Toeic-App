package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.ZipFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/toeic-storage")
public class ToeicStorageController {
    @Autowired
    private ToeicStorageService toeicStorageService;

    @PostMapping("uploads")
    public BaseResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return this.toeicStorageService.save(file);
    }

    @PostMapping("upload-zip-file")
    public BaseResponse uploadZipFile(
            @RequestParam("file") MultipartFile zipFile) throws IOException {
        return this.toeicStorageService.uploadZipFile(zipFile);
    }

    @GetMapping("downloads/{id}")
    public ResponseEntity downloadFile(
            @PathVariable Integer id) throws IOException {
        final byte[] buffer = this.toeicStorageService.downloadFile(id);
        if (buffer == null) {
            return ResponseEntity.notFound().build();
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        InputStreamResource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=toeic")
                // Content-Type
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                // Contet-Length
                .contentLength(buffer.length) //
                .body(resource);
    }


}
