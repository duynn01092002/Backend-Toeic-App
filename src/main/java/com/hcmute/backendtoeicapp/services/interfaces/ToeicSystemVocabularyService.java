package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.CreateToeicVocabTopicRequest;
import com.hcmute.backendtoeicapp.dto.toeicvocabtopic.UpdateToeicVocabTopicRequest;

public interface ToeicSystemVocabularyService {
    BaseResponse createTopic(CreateToeicVocabTopicRequest request);
    BaseResponse listAllTopics();
    BaseResponse updateTopic(UpdateToeicVocabTopicRequest request);
    BaseResponse deleteTopicById(Integer topicId);
}
