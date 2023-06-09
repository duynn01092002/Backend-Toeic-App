package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ToeicSystemVocabularyService {
    BaseResponse createWord(CreateToeicWordRequest request);
    BaseResponse createTopic(CreateToeicVocabTopicRequest request);
    BaseResponse addWordAudio(AddWordAudioRequest request);

    BaseResponse listAllTopics();
    BaseResponse listAllWordsByTopicId(Integer topicId);
    BaseResponse getWordDetail(Integer wordId);

    BaseResponse updateTopic(UpdateToeicVocabTopicRequest request);
    BaseResponse updateWordInformation(UpdateWordInformationRequest request);

    BaseResponse deleteTopicById(Integer topicId);
    BaseResponse deleteWordAudioById(Integer audioId);
    BaseResponse deleteWordById(Integer wordId);

    BaseResponse restoreToeicBackupZipFile(MultipartFile uploadedBackupFile);
    byte[] downloadBackupZip() throws IOException;
}
