package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.AppConfiguration;
import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicStorageRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ToeicStorageServiceImpl implements ToeicStorageService {
    @Autowired
    private ToeicStorageRepository toeicStorageRepository;

    @Autowired
    private AppConfiguration appConfiguration;



    @Override
    public void init() {
        try {
            Path root = Paths.get(this.appConfiguration.getToeicStoreDirectory());
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Không tạo được folder để upload !");
        }
    }

    @Override
    public BaseResponse save(MultipartFile file) {
        try {
            String fileName = randomFileName(file.getOriginalFilename());
            Path root = Paths.get(this.appConfiguration.getToeicStoreDirectory());
            Files.copy(file.getInputStream(), root.resolve(fileName));
            ToeicStorageEntity toeicStorageEntity = new ToeicStorageEntity();
            toeicStorageEntity.setFileName(fileName);
            this.toeicStorageRepository.save(toeicStorageEntity);

            SuccessfulResponse response = new SuccessfulResponse();
            response.setMessage("Upload file thành công");
            response.setData(toeicStorageEntity);
            return response;
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("Tên file đã tồn tại");
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public byte[] downloadFile(Integer id) throws IOException {
        ToeicStorageEntity toeicStorageEntity = this.toeicStorageRepository.getById(id);
        Path path = Paths.get(this.appConfiguration.getToeicStoreDirectory(), toeicStorageEntity.getFileName());
        byte[] datas = Files.readAllBytes(path);
        return datas;
    }

    private static String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

    private static String randomFileName(String fileName) {
        return UUID.randomUUID() + getFileExtension(fileName);
    }
}
