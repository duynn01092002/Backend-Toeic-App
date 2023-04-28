package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.AppConfiguration;
import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicStorageRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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
    public ToeicStorageEntity saveUploadedFileAndReturnEntity(MultipartFile uploadedFile) throws IOException {
        String fileName = randomFileName(uploadedFile.getOriginalFilename());
        Path root = Paths.get(this.appConfiguration.getToeicStoreDirectory());
        Files.copy(uploadedFile.getInputStream(), root.resolve(fileName));
        ToeicStorageEntity toeicStorageEntity = new ToeicStorageEntity();
        toeicStorageEntity.setFileName(fileName);
        this.toeicStorageRepository.save(toeicStorageEntity);
        return toeicStorageEntity;
    }

    @Override
    public ToeicStorageEntity saveByteArrayAndReturnEntity(String ext, byte[] stream) throws IOException {
        String fileName = randomFileName(ext);
        Path root = Paths.get(this.appConfiguration.getToeicStoreDirectory());
        Files.copy(new ByteArrayInputStream(stream), root.resolve(fileName));
        ToeicStorageEntity toeicStorageEntity = new ToeicStorageEntity();
        toeicStorageEntity.setFileName(fileName);
        this.toeicStorageRepository.save(toeicStorageEntity);
        return toeicStorageEntity;
    }

    @Override
    public Map<String, Object> getFileNameAndStream(Integer id) {
        if (!this.toeicStorageRepository.existsById(id)) {
            throw new RuntimeException("Not found file with id = " + id);
        }

        final Map<String, Object> result = new HashMap<>();
        final ToeicStorageEntity toeicStorageEntity = toeicStorageRepository.findById(id).get();

        if (toeicStorageEntity.getFileName().lastIndexOf('.') < 0)
            result.put("fileName", toeicStorageEntity.getFileName() + ".bin");
        else
            result.put("fileName", toeicStorageEntity.getFileName());

        final File refFile = new File(this.appConfiguration.getToeicStoreDirectory(), toeicStorageEntity.getFileName());

        try {
            final byte[] stream = new FileInputStream(refFile).readAllBytes();
            result.put("stream", stream);
        }
        catch (IOException ignored) {
            throw new RuntimeException("Read file error!");
        }

        return result;
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

    @Override
    public BaseResponse uploadZipFile(MultipartFile zipFile) throws IOException {
        String fileZipName = zipFile.getOriginalFilename();
        String fileExtension = ".zip";
        if (!getFileExtension(fileZipName).equals(fileExtension)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("File upload phải là file zip");
            return response;
        }
        InputStream inputStream = zipFile.getInputStream();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);


        ZipEntry zipEntry;

        List<ToeicStorageEntity> toeicStorageEntities = new ArrayList<>();

        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            String fileName = randomFileName(zipEntry.getName());

            Path root = Paths.get(this.appConfiguration.getToeicStoreDirectory());
            if (zipEntry.isDirectory()) {
                File file = new File(root.resolve(zipEntry.getName()).toString());
                if (!file.exists()){
                    file.mkdirs();
                }
                ToeicStorageEntity toeicStorageEntity = new ToeicStorageEntity();
                toeicStorageEntity.setFileName(fileName);
                this.toeicStorageRepository.save(toeicStorageEntity);
                toeicStorageEntities.add(toeicStorageEntity);
            }
            else {
                File file = new File(root.resolve(zipEntry.getName()).toString());
                FileOutputStream os = new FileOutputStream(file);
                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                    os.write(c);
                }
                os.close();


                ToeicStorageEntity toeicStorageEntity = new ToeicStorageEntity();
                toeicStorageEntity.setFileName(fileName);
                this.toeicStorageRepository.save(toeicStorageEntity);
                toeicStorageEntities.add(toeicStorageEntity);
            }

        }

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Upload file zip thành công");
        response.setData(toeicStorageEntities);
        return response;
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
