package com.hcmute.backendtoeicapp.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.CreateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.ToeicVocabTopicResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.UpdateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import com.hcmute.backendtoeicapp.entities.ToeicVocabTopicEntity;
import com.hcmute.backendtoeicapp.entities.ToeicVocabWordAudioEntity;
import com.hcmute.backendtoeicapp.entities.ToeicVocabWordEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicVocabTopicRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicVocabWordAudioRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicVocabWordRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicStorageService;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicSystemVocabularyService;
import com.hcmute.backendtoeicapp.services.vocabbackup.model.ToeicVocabAudioBackupModel;
import com.hcmute.backendtoeicapp.services.vocabbackup.model.ToeicVocabTopicBackupModel;
import com.hcmute.backendtoeicapp.services.vocabbackup.model.ToeicVocabWordBackupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ToeicSystemVocabularyServiceImpl implements ToeicSystemVocabularyService {
    @Autowired
    private ToeicVocabTopicRepository toeicVocabTopicRepository;

    @Autowired
    private ToeicVocabWordAudioRepository toeicVocabWordAudioRepository;

    @Autowired
    private ToeicVocabWordRepository toeicVocabWordRepository;

    @Autowired
    private ToeicStorageService toeicStorageService;

    private static Gson gsonInstance = new GsonBuilder()
            .create();

    public ToeicSystemVocabularyServiceImpl() {

    }

    @Override
    public BaseResponse createTopic(CreateToeicVocabTopicRequest request) {
        if (request.getTopicName() == null || request.getTopicName().length() == 0) {
            throw new RuntimeException("Tên chủ đề không được trống");
        }

        ToeicVocabTopicEntity toeicVocabTopicEntity = new ToeicVocabTopicEntity();
        toeicVocabTopicEntity.setTopicName(request.getTopicName());
        this.toeicVocabTopicRepository.save(toeicVocabTopicEntity);

        if (request.getUploadedTopicImage() != null) {
            try {
                final ToeicStorageEntity toeicStorageEntity = this.toeicStorageService.saveUploadedFileAndReturnEntity(
                        request.getUploadedTopicImage()
                );

                toeicVocabTopicEntity.setToeicTopicImage(toeicStorageEntity);

                this.toeicVocabTopicRepository.save(toeicVocabTopicEntity);
            } catch (IOException e) {
                throw new RuntimeException("Đọc file upload lên lỗi: " + e.getMessage());
            }
        }

        SuccessfulResponse response = new SuccessfulResponse();
        response.setData(new ToeicVocabTopicResponse(toeicVocabTopicEntity));
        response.setMessage("Tạo chủ đề thành công");
        return response;
    }

    @Override
    public BaseResponse listAllTopics() {
        final List<ToeicVocabTopicEntity> toeicVocabTopicEntities = this.toeicVocabTopicRepository.findAll();

        final List<ToeicVocabTopicResponse> toeicVocabTopicResponses = toeicVocabTopicEntities
                .stream()
                .map(ToeicVocabTopicResponse::new)
                .toList();

        SuccessfulResponse response = new SuccessfulResponse();
        response.setData(toeicVocabTopicResponses);
        response.setMessage("Lấy dữ liệu thành công");

        return response;
    }

    @Override
    public BaseResponse updateTopic(UpdateToeicVocabTopicRequest request) {
        final Optional<ToeicVocabTopicEntity> refEntity = this.toeicVocabTopicRepository.findById(request.getTopicId());

        if (refEntity.isEmpty()) {
            throw new RuntimeException("Không tìm thấy chủ đề nào với id = " + request.getTopicId());
        }

        if (request.getTopicName() == null || request.getTopicName().length() == 0) {
            throw new RuntimeException("Tên chủ đề không được trống");
        }

        final ToeicVocabTopicEntity entity = refEntity.get();

        entity.setTopicName(request.getTopicName());

        if (request.getUploadedTopicImage() != null) {
            try {
                final ToeicStorageEntity toeicStorageEntity = this.toeicStorageService.saveUploadedFileAndReturnEntity(
                        request.getUploadedTopicImage()
                );

                entity.setToeicTopicImage(toeicStorageEntity);

                this.toeicVocabTopicRepository.save(entity);
            } catch (IOException e) {
                throw new RuntimeException("Đọc file upload lên lỗi: " + e.getMessage());
            }
        }

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Cập nhật chủ đề thành công");
        response.setData(new ToeicVocabTopicResponse(entity));

        return response;
    }

    @Override
    public BaseResponse deleteTopicById(Integer topicId) {
        final Optional<ToeicVocabTopicEntity> refEntity = this.toeicVocabTopicRepository.findById(topicId);

        if (refEntity.isEmpty()) {
            throw new RuntimeException("Không tìm thấy chủ đề nào với id = " + topicId);
        }

        this.toeicVocabTopicRepository.delete(refEntity.get());

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Xóa chủ đề thành công");
        return response;
    }

    private void restoreBackupFile(
            final InputStream inputStream
    ) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        ZipEntry zipEntry = null;
        Map<String, byte[]> fileStreamMapping = new Hashtable<>();

        List<ToeicVocabTopicBackupModel> topics = null;

        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (zipEntry.isDirectory())
                continue;

            final String fileName = zipEntry.getName();
            final byte[] buffer = zipInputStream.readAllBytes();

            if (fileName.equals("config.json")) {
                final String json = new String(buffer, StandardCharsets.UTF_8);
                topics = List.of(gsonInstance.fromJson(json, ToeicVocabTopicBackupModel[].class));
            }
            else {
                fileStreamMapping.put(fileName, buffer);
            }
        }

        if (topics == null) {
            throw new RuntimeException("Not found config.json");
        }

        this.toeicVocabTopicRepository.deleteAll();

        for (ToeicVocabTopicBackupModel topicModel : topics) {
            if (!fileStreamMapping.containsKey(topicModel.getImg())) {
                throw new RuntimeException("Not found " + topicModel.getImg());
            }

            ToeicVocabTopicEntity topicEntity = new ToeicVocabTopicEntity();
            topicEntity.setTopicName(topicModel.getTopicName());

            ToeicStorageEntity topicImageStorageEntity =
                    this.toeicStorageService.saveByteArrayAndReturnEntity(
                            topicModel.getImg(),
                            fileStreamMapping.get(topicModel.getImg())
                    );

            topicEntity.setToeicTopicImage(topicImageStorageEntity);

            this.toeicVocabTopicRepository.save(topicEntity);

            for (ToeicVocabWordBackupModel wordModel : topicModel.getWordList()) {
                ToeicVocabWordEntity wordEntity = new ToeicVocabWordEntity();
                wordEntity.setEnglish(wordModel.getEng());
                wordEntity.setVietnamese(wordModel.getViet());
                wordEntity.setPronounce(wordModel.getPronounce());
                wordEntity.setExampleVietnamese(wordModel.getExampleViet());
                wordEntity.setExampleEnglish(wordModel.getExampleEng());

                if (wordModel.getImg() != null) {
                    if (!fileStreamMapping.containsKey(wordModel.getImg())) {
                        throw new RuntimeException("Not found " + wordModel.getImg());
                    }

                    ToeicStorageEntity wordImageStorageEntity =
                            this.toeicStorageService.saveByteArrayAndReturnEntity(
                                    wordModel.getImg(),
                                    fileStreamMapping.get(wordModel.getImg())
                            );

                    wordEntity.setWordImage(wordImageStorageEntity);
                }

                this.toeicVocabWordRepository.save(wordEntity);

                for (ToeicVocabAudioBackupModel audioModel : wordModel.getAudio()) {
                    ToeicVocabWordAudioEntity audioEntity = new ToeicVocabWordAudioEntity();
                    audioEntity.setVoice(audioModel.getSpeaker());

                    ToeicStorageEntity audioStorageEntity =
                            this.toeicStorageService.saveByteArrayAndReturnEntity(
                                    audioModel.getFile(),
                                    fileStreamMapping.get(audioModel.getFile())
                            );

                    audioEntity.setAudioStorage(audioStorageEntity);

                    this.toeicVocabWordAudioRepository.save(audioEntity);
                }
            }
        }
    }

    @Override
    public BaseResponse restoreToeicBackupZipFile(MultipartFile uploadedBackupFile) {
        try {
            this.restoreBackupFile(new ByteArrayInputStream(uploadedBackupFile.getBytes()));
        } catch (IOException ignored) {
            throw new RuntimeException("Restore zip file error!");
        }

        SuccessfulResponse response = new SuccessfulResponse();
        response.setData("Backup OK");
        return response;
    }
}
