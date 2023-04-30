package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.ToeicAnswerChoiceResponse;
import com.hcmute.backendtoeicapp.dto.toeicNewQuestion.CreateNewQuestionRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.CreateToeicQuestionRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.ToeicQuestionResponse;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.UpdateToeicQuestionRequest;
import com.hcmute.backendtoeicapp.entities.ToeicAnswerChoiceEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicAnswerChoiceRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicQuestionGroupRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicQuestionRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ToeicQuestionServiceImpl implements ToeicQuestionService {
    @Autowired
    private ToeicQuestionRepository toeicQuestionRepository;

    @Autowired
    private ToeicQuestionGroupRepository toeicQuestionGroupRepository;

    @Autowired
    private ToeicAnswerChoiceRepository toeicAnswerChoiceRepository;

    private static List<String> getCorrectAnswers() {
        List<String> correctAnswers = new ArrayList<>();
        correctAnswers.add("A");
        correctAnswers.add("B");
        correctAnswers.add("C");
        correctAnswers.add("D");
        return correctAnswers;
    }

    @Override
    public BaseResponse getToeicQuestionById(Integer id) {
        if (!this.toeicQuestionRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question với id = " + id);
            return response;
        }
        ToeicQuestionEntity toeicQuestionEntity = this.toeicQuestionRepository.getById(id);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicQuestionEntity);
        return response;
    }

    @Override
    public BaseResponse getAllToeicQuestions() {
        List<ToeicQuestionEntity> toeicQuestionEntityList = this.toeicQuestionRepository.findAll();
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicQuestionEntityList);
        return response;
    }

    @Override
    public BaseResponse getToeicQuestionsByGroupId(Integer groupId) {
        if (!this.toeicQuestionGroupRepository.existsById(groupId)) {
            throw new RuntimeException("Không tìm thấy group nào với id = " + groupId);
        }

        final List<ToeicQuestionEntity> result = this.toeicQuestionRepository.getToeicQuestionEntitiesByToeicQuestionGroup(groupId);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(result);

        return response;
    }

    @Override
    public BaseResponse createToeicQuestion(CreateToeicQuestionRequest request) {
        if (!this.toeicQuestionGroupRepository.existsById(request.getToeicQuestionGroupId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question group với id = " + request.getToeicQuestionGroupId());
            return response;
        }
        if (!(getCorrectAnswers().contains(request.getCorrectAnswer()))) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Đáp án đúng phải thuộc A, B, C, D");
            return response;
        }
        ToeicQuestionEntity toeicQuestionEntity = new ToeicQuestionEntity();
        toeicQuestionEntity.setQuestionNumber(request.getQuestionNumber());
        toeicQuestionEntity.setCorrectAnswer(request.getCorrectAnswer());
        toeicQuestionEntity.setToeicQuestionGroupEntity(
                this.toeicQuestionGroupRepository.getById(request.getToeicQuestionGroupId()));
        this.toeicQuestionRepository.save(toeicQuestionEntity);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo dữ liệu thành công");
        response.setData(toeicQuestionEntity);
        return response;
    }
    @Override
    public BaseResponse updateToeicQuestion(UpdateToeicQuestionRequest request) {
        if (!this.toeicQuestionGroupRepository.existsById(request.getToeicQuestionGroupId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question group với id = " + request.getToeicQuestionGroupId());
            return response;
        }
        if (!this.toeicQuestionRepository.existsById(request.getId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question với id = " + request.getId());
            return response;
        }
        if (!(getCorrectAnswers().contains(request.getCorrectAnswer()))) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Đáp án đúng phải thuộc A, B, C, D");
            return response;
        }
        ToeicQuestionEntity toeicQuestionEntity = this.toeicQuestionRepository.getById(request.getId());
        toeicQuestionEntity.setQuestionNumber(request.getQuestionNumber());
        toeicQuestionEntity.setToeicQuestionGroupEntity(
                this.toeicQuestionGroupRepository.getById(request.getToeicQuestionGroupId()));
        this.toeicQuestionRepository.save(toeicQuestionEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Cập nhật dữ liệu thành công");
        response.setData(toeicQuestionEntity);
        return response;
    }
    @Override
    public BaseResponse deleteToeicQuestion(Integer id) {
        if (!this.toeicQuestionRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question với id = " + id);
            return response;
        }
        ToeicQuestionEntity toeicQuestionEntity = this.toeicQuestionRepository.getById(id);
        this.toeicQuestionRepository.delete(toeicQuestionEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Xóa dữ liệu thành công");
        return response;
    }

    @Override
    public BaseResponse createNewQuestion(CreateNewQuestionRequest request) {
        if (!this.toeicQuestionGroupRepository.existsById(request.getToeicQuestionGroupId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question group với id = " + request.getToeicQuestionGroupId());
            return response;
        }
        if (request.getToeicAnswers().size() > 4 && request.getToeicAnswers().size() < 3) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Danh sách đáp án không hợp lệ");
            return response;
        }
        List<String> answerList = new ArrayList<>();
        for (ToeicAnswerChoiceEntity answer : request.getToeicAnswers()) {
            answerList.add(answer.getLabel());
        }
        for (int i = 0;i<answerList.size();i++) {
            if (!(getCorrectAnswers().contains(answerList.get(i)))) {
                ErrorResponse response = new ErrorResponse();
                response.setMessage("Đáp án phải thuộc A, B, C, D");
                return response;
            }
        }
        Set<String> set = new HashSet<>(answerList);
        if (set.size() != answerList.size()) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Danh sách đáp án không hợp lệ");
            return response;
        }
        if (!(getCorrectAnswers().contains(request.getCorrectAnswer()))) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Đáp án đúng phải thuộc A, B, C, D");
            return response;
        }
        ToeicQuestionEntity newQuestion = new ToeicQuestionEntity();
        newQuestion.setCorrectAnswer(request.getCorrectAnswer());
        newQuestion.setQuestionNumber(request.getQuestionNumber());
        newQuestion.setContent(request.getContent());
        newQuestion.setToeicQuestionGroupEntity(
                this.toeicQuestionGroupRepository.getById(request.getToeicQuestionGroupId()));
        this.toeicQuestionRepository.save(newQuestion);
        for (ToeicAnswerChoiceEntity answerChoice : request.getToeicAnswers()) {
            answerChoice.setToeicQuestionEntity(newQuestion);
            this.toeicAnswerChoiceRepository.save(answerChoice);
        }
        List<ToeicAnswerChoiceEntity> choices =
                this.toeicAnswerChoiceRepository.getListChoicesByQuestionId(newQuestion.getId());
        List<ToeicAnswerChoiceResponse> answerChoiceResponses =
                choices.stream().map(ToeicAnswerChoiceResponse::new).toList();
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo câu hỏi mới thành công");
        ToeicQuestionResponse questionResponse = new ToeicQuestionResponse();
        questionResponse.setQuestionNumber(newQuestion.getQuestionNumber());
        questionResponse.setContent(newQuestion.getContent());
        questionResponse.setCorrectAnswer(newQuestion.getCorrectAnswer());
        questionResponse.setId(newQuestion.getId());
        questionResponse.setChoices(answerChoiceResponses);
        response.setData(questionResponse);
        return response;
    }
}
