package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.ZipFile;

public interface ToeicStorageService {
    void init();

    BaseResponse save(MultipartFile file);

    byte[] downloadFile(Integer id) throws IOException;

    BaseResponse uploadZipFile(MultipartFile zipFile) throws IOException;
}
