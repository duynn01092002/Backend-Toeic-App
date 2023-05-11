package com.hcmute.backendtoeicapp.services.android;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.android.tests.*;
import com.hcmute.backendtoeicapp.entities.ToeicItemContentEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionGroupEntity;
import com.hcmute.backendtoeicapp.repositories.*;
import com.hcmute.backendtoeicapp.services.interfaces.AndroidToeicTestService;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicFullTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AndroidToeicTestServiceImpl implements AndroidToeicTestService {
    @Autowired
    private ToeicFullTestRepository toeicFullTestRepository;

    @Autowired
    private ToeicPartRepository toeicPartRepository;

    @Autowired
    private ToeicQuestionGroupRepository toeicQuestionGroupRepository;

    @Autowired
    private ToeicQuestionRepository toeicQuestionRepository;

    @Autowired
    private ToeicAnswerChoiceRepository toeicAnswerChoiceRepository;

    @Autowired
    private ToeicItemContentRepository toeicItemContentRepository;

    public AndroidToeicTestServiceImpl() {

    }

    private AndroidItemContent processItemContent(ToeicItemContentEntity entity) {
        AndroidItemContent androidItemContent = new AndroidItemContent();
        androidItemContent.setContentType(entity.getContentType());

        if (entity.getContentType().equals("AUDIO") || entity.getContentType().equals("IMAGE")) {
            androidItemContent.setStorageServerId(androidItemContent.getStorageServerId());
        }
        else {
            androidItemContent.setRawContent(entity.getContent());
        }

        return androidItemContent;
    }

    @Override
    public BaseResponse getAllTestsData() {
        final List<AndroidToeicFullTest> androidToeicFullTests = this.toeicFullTestRepository
                .findAll()
                .stream()
                .map(AndroidToeicFullTest::new)
                .toList();

        for (AndroidToeicFullTest androidToeicFullTest : androidToeicFullTests) {
            final List<AndroidToeicPart> androidToeicParts = this.toeicPartRepository
                    .getToeicPartEntitiesByToeicFullTestId(androidToeicFullTest.getServerId())
                    .stream()
                    .map(AndroidToeicPart::new)
                    .toList();

            androidToeicFullTest.setParts(androidToeicParts);

            for (AndroidToeicPart androidToeicPart : androidToeicParts) {
                final List<ToeicQuestionGroupEntity> toeicQuestionGroupEntities = this.toeicQuestionGroupRepository
                        .getToeicQuestionGroupEntitiesByToeicPartEntity(androidToeicPart.getServerId());

                final List<AndroidToeicQuestionGroup> androidToeicQuestionGroups = new ArrayList<>();

                for (ToeicQuestionGroupEntity toeicQuestionGroupEntity : toeicQuestionGroupEntities) {
                    AndroidToeicQuestionGroup androidToeicQuestionGroup = new AndroidToeicQuestionGroup(toeicQuestionGroupEntity);

                    final List<AndroidItemContent> questionContents = this.toeicItemContentRepository
                            .getListQuestionContentByQuestionGroupId(toeicQuestionGroupEntity.getId())
                            .stream()
                            .map(this::processItemContent)
                            .toList();

                    final List<AndroidItemContent> transcriptContents = this.toeicItemContentRepository
                            .getListTranscriptByQuestionGroupId(toeicQuestionGroupEntity.getId())
                            .stream()
                            .map(this::processItemContent)
                            .toList();

                    androidToeicQuestionGroup.setQuestionContents(questionContents);
                    androidToeicQuestionGroup.setTranscriptContents(transcriptContents);

                    androidToeicQuestionGroups.add(androidToeicQuestionGroup);
                }

                androidToeicPart.setGroups(androidToeicQuestionGroups);

                for (AndroidToeicQuestionGroup androidToeicQuestionGroup : androidToeicQuestionGroups) {
                    final List<AndroidToeicQuestion> androidToeicQuestions = this.toeicQuestionRepository
                            .getToeicQuestionEntitiesByToeicQuestionGroup(androidToeicQuestionGroup.getServerId())
                            .stream()
                            .map(AndroidToeicQuestion::new)
                            .toList();

                    androidToeicQuestionGroup.setQuestions(androidToeicQuestions);

                    for (AndroidToeicQuestion androidToeicQuestion : androidToeicQuestions) {
                        final List<AndroidAnswerChoice> androidAnswerChoices = this.toeicAnswerChoiceRepository
                                .getListChoicesByQuestionId(androidToeicQuestion.getServerId())
                                .stream()
                                .map(AndroidAnswerChoice::new)
                                .toList();

                        androidToeicQuestion.setChoices(androidAnswerChoices);
                    }
                }
            }
        }

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(androidToeicFullTests);
        return response;
    }
}
