package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ToeicBackupService {
    byte[] backupToeicTest(Integer id);
    BaseResponse restoreToeicTest(MultipartFile file) throws IOException;
}
