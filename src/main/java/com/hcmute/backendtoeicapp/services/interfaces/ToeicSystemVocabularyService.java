package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.AddWordAudioRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.CreateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.UpdateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.UpdateWordInformationRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ToeicSystemVocabularyService {
    BaseResponse createTopic(CreateToeicVocabTopicRequest request);
    BaseResponse addWordAudio(AddWordAudioRequest request);

    BaseResponse listAllTopics();
    BaseResponse listAllWordsByTopicId(Integer topicId);
    BaseResponse getWordDetail(Integer wordId);

    BaseResponse updateTopic(UpdateToeicVocabTopicRequest request);
    BaseResponse updateWordInformation(UpdateWordInformationRequest request);

    BaseResponse deleteTopicById(Integer topicId);
    BaseResponse deleteWordAudioById(Integer audioId);

    BaseResponse restoreToeicBackupZipFile(MultipartFile uploadedBackupFile);
}
