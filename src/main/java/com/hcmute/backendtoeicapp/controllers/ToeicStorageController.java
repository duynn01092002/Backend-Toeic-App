package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicStorage.GetListStorageEntityRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.awt.datatransfer.MimeTypeParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.FileSystemNotFoundException;
import java.util.Map;
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

    @Deprecated
    @PostMapping("upload-zip-file")
    public BaseResponse uploadZipFile(
            @RequestParam("file") MultipartFile zipFile) throws IOException {
        return this.toeicStorageService.uploadZipFile(zipFile);
    }

    @GetMapping("view/{id}")
    public ResponseEntity viewFile(
            @PathVariable Integer id
    ) {
        final Map<String, Object> temp = this.toeicStorageService.getFileNameAndStream(id);
        final String fileName = temp.get("fileName").toString();
        final byte[] stream = (byte[])temp.get("stream");

        final MediaType mediaType = MediaType.parseMediaType(
                URLConnection.guessContentTypeFromName(fileName)
        );

        ByteArrayInputStream inputStream = new ByteArrayInputStream(stream);
        InputStreamResource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(mediaType)
                .contentLength(stream.length)
                .body(resource);
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

    @PostMapping("get-list-storage")
    public ResponseEntity getListStorageFile(@RequestBody GetListStorageEntityRequest request) throws IOException {
        final byte[] buffer = this.toeicStorageService.getListStorageEntity(request);
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
