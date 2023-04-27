package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import jakarta.annotation.Nonnull;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipFile;

public interface ToeicStorageService {
    void init();

    ToeicStorageEntity saveUploadedFileAndReturnEntity(
            @Nonnull MultipartFile uploadedFile
    ) throws IOException;

    Map<String, Object> getFileNameAndStream(Integer id);

    BaseResponse save(MultipartFile file);

    byte[] downloadFile(Integer id) throws IOException;

    @Deprecated
    BaseResponse uploadZipFile(MultipartFile zipFile) throws IOException;
}
