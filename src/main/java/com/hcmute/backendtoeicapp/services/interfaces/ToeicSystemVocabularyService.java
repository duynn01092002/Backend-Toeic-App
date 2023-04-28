package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.CreateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.UpdateToeicVocabTopicRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ToeicSystemVocabularyService {
    BaseResponse createTopic(CreateToeicVocabTopicRequest request);
    BaseResponse listAllTopics();
    BaseResponse updateTopic(UpdateToeicVocabTopicRequest request);
    BaseResponse deleteTopicById(Integer topicId);

    BaseResponse restoreToeicBackupZipFile(MultipartFile uploadedBackupFile);
}
