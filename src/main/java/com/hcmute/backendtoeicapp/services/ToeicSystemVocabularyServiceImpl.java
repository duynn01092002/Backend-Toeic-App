package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.CreateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.ToeicVocabTopicResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.UpdateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import com.hcmute.backendtoeicapp.entities.ToeicVocabTopicEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicVocabTopicRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicVocabWordAudioRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicVocabWordListRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicStorageService;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicSystemVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ToeicSystemVocabularyServiceImpl implements ToeicSystemVocabularyService {
    @Autowired
    private ToeicVocabTopicRepository toeicVocabTopicRepository;

    @Autowired
    private ToeicVocabWordAudioRepository toeicVocabWordAudioRepository;

    @Autowired
    private ToeicVocabWordListRepository toeicVocabWordListRepository;

    @Autowired
    private ToeicStorageService toeicStorageService;

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
        response.setData(entity);

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
}
