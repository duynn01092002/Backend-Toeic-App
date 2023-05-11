package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.entities.*;
import com.hcmute.backendtoeicapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SyncToeicTestService {
    @Autowired
    private ToeicFullTestRepository toeicFullTestRepository;

    @Autowired
    private ToeicPartRepository toeicPartRepository;

    @Autowired
    private ToeicAnswerChoiceRepository toeicAnswerChoiceRepository;

    @Autowired
    private ToeicItemContentRepository toeicItemContentRepository;

    @Autowired
    private ToeicQuestionGroupRepository toeicQuestionGroupRepository;

    @Autowired
    private ToeicQuestionRepository toeicQuestionRepository;

    public void updateToeicFullTestEntity(ToeicFullTestEntity entity) {
        entity.setCheckSum(UUID.randomUUID().toString());
        this.toeicFullTestRepository.save(entity);
    }

    public void updateToeicPartEntity(ToeicPartEntity entity) {
        ToeicFullTestEntity toeicFullTestEntity =
                this.toeicFullTestRepository.getToeicFullTestEntityByToeicPartId(entity.getId());
        toeicFullTestEntity.setCheckSum(UUID.randomUUID().toString());
        this.toeicFullTestRepository.save(toeicFullTestEntity);
    }

    public void updateToeicQuestionGroupEntity(ToeicQuestionGroupEntity entity) {
        ToeicPartEntity toeicPartEntity =
                this.toeicPartRepository.getToeicPartEntityByQuestionGroupId(entity.getId());
        ToeicFullTestEntity toeicFullTestEntity =
                this.toeicFullTestRepository.getToeicFullTestEntityByToeicPartId(toeicPartEntity.getId());
        toeicFullTestEntity.setCheckSum(UUID.randomUUID().toString());
        this.toeicFullTestRepository.save(toeicFullTestEntity);
    }

    public void updateToeicQuestionEntity(ToeicQuestionEntity entity) {
        ToeicQuestionGroupEntity toeicQuestionGroupEntity =
                this.toeicQuestionGroupRepository.getToeicQuestionGroupEntityByQuestionId(entity.getId());
        ToeicPartEntity toeicPartEntity =
                this.toeicPartRepository.getToeicPartEntityByQuestionGroupId(toeicQuestionGroupEntity.getId());
        ToeicFullTestEntity toeicFullTestEntity =
                this.toeicFullTestRepository.getToeicFullTestEntityByToeicPartId(toeicPartEntity.getId());
        toeicFullTestEntity.setCheckSum(UUID.randomUUID().toString());
        this.toeicFullTestRepository.save(toeicFullTestEntity);
    }

    public void updateToeicAnswerChoiceEntity(ToeicAnswerChoiceEntity entity) {
        ToeicQuestionEntity toeicQuestionEntity =
                this.toeicQuestionRepository.getToeicQuestionEntityByAnswerChoiceId(entity.getId());
        ToeicQuestionGroupEntity toeicQuestionGroupEntity =
                this.toeicQuestionGroupRepository.getToeicQuestionGroupEntityByQuestionId(toeicQuestionEntity.getId());
        ToeicPartEntity toeicPartEntity =
                this.toeicPartRepository.getToeicPartEntityByQuestionGroupId(toeicQuestionGroupEntity.getId());
        ToeicFullTestEntity toeicFullTestEntity =
                this.toeicFullTestRepository.getToeicFullTestEntityByToeicPartId(toeicPartEntity.getId());
        toeicFullTestEntity.setCheckSum(UUID.randomUUID().toString());
        this.toeicFullTestRepository.save(toeicFullTestEntity);
    }

    public void updateToeicItemContentRepository(ToeicItemContentEntity entity) {
        ToeicQuestionGroupEntity toeicQuestionGroupEntity =
                this.toeicQuestionGroupRepository.getToeicQuestionGroupEntityByQuestionContentId(entity.getId());
        if (toeicQuestionGroupEntity == null)
            toeicQuestionGroupEntity =
                    this.toeicQuestionGroupRepository.getToeicQuestionGroupEntityByTranscriptId(entity.getId());
        ToeicPartEntity toeicPartEntity =
                this.toeicPartRepository.getToeicPartEntityByQuestionGroupId(toeicQuestionGroupEntity.getId());
        ToeicFullTestEntity toeicFullTestEntity =
                this.toeicFullTestRepository.getToeicFullTestEntityByToeicPartId(toeicPartEntity.getId());
        toeicFullTestEntity.setCheckSum(UUID.randomUUID().toString());
        this.toeicFullTestRepository.save(toeicFullTestEntity);
    }



}
