package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.CreateToeicAnswerChoiceRequest;
import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.UpdateToeicAnswerChoiceRequest;
import com.hcmute.backendtoeicapp.entities.ToeicAnswerChoiceEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicAnswerChoiceRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicQuestionRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicAnswerChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToeicAnswerChoiceServiceImpl implements ToeicAnswerChoiceService {
    @Autowired
    private ToeicAnswerChoiceRepository toeicAnswerChoiceRepository;

    @Autowired
    private ToeicQuestionRepository toeicQuestionRepository;

    @Autowired
    private SyncToeicTestService syncToeicTestService;

    @Override
    public BaseResponse getToeicAnswerChoiceById(Integer id) {
        if (!this.toeicAnswerChoiceRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic answer choice với id = " + id);
            return response;
        }
        ToeicAnswerChoiceEntity toeicAnswerChoiceEntity = this.toeicAnswerChoiceRepository.getById(id);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicAnswerChoiceEntity);
        return response;
    }

    @Override
    public BaseResponse getAllToeicAnswerChoices() {
        List<ToeicAnswerChoiceEntity> toeicAnswerChoiceEntityList = this.toeicAnswerChoiceRepository.findAll();
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicAnswerChoiceEntityList);
        return response;
    }
    private static List<String> getChoices() {
        List<String> choices = new ArrayList<>();
        choices.add("A");
        choices.add("B");
        choices.add("C");
        choices.add("D");
        return choices;
    }

    @Override
    public BaseResponse createToeicAnswerChoice(CreateToeicAnswerChoiceRequest request) {
        List<String> choices = getChoices();
        if (!this.toeicQuestionRepository.existsById(request.getToeicQuestionId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question với id = " + request.getToeicQuestionId());
            return response;
        }
        if (!choices.contains(request.getLabel())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Đáp án phải là một trong 4 đáp án A, B, C, D");
            return response;
        }
        ToeicAnswerChoiceEntity toeicAnswerChoiceEntity = new ToeicAnswerChoiceEntity();
        toeicAnswerChoiceEntity.setLabel(request.getLabel());
        toeicAnswerChoiceEntity.setContent(request.getContent());
        toeicAnswerChoiceEntity.setExplain(request.getExplain());
        toeicAnswerChoiceEntity.setToeicQuestionEntity(
                this.toeicQuestionRepository.getById(request.getToeicQuestionId()));
        this.toeicAnswerChoiceRepository.save(toeicAnswerChoiceEntity);
        this.syncToeicTestService.updateToeicAnswerChoiceEntity(toeicAnswerChoiceEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo dữ liệu thành công");
        response.setData(toeicAnswerChoiceEntity);
        return response;
    }

    @Override
    public BaseResponse updateToeicAnswerChoice(UpdateToeicAnswerChoiceRequest request) {
        List<String> choices = getChoices();
        if (!this.toeicAnswerChoiceRepository.existsById(request.getId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic answer choice với id = " + request.getId());
            return response;
        }
        if (!choices.contains(request.getLabel())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Đáp án phải là một trong 4 đáp án A, B, C, D");
            return response;
        }
        if (!this.toeicQuestionRepository.existsById(request.getToeicQuestionId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic question với id = " + request.getToeicQuestionId());
            return response;
        }
        ToeicAnswerChoiceEntity toeicAnswerChoiceEntity = this.toeicAnswerChoiceRepository.getById(request.getId());
        toeicAnswerChoiceEntity.setLabel(request.getLabel());
        toeicAnswerChoiceEntity.setContent(request.getContent());
        toeicAnswerChoiceEntity.setExplain(request.getExplain());
        toeicAnswerChoiceEntity.setToeicQuestionEntity(
                this.toeicQuestionRepository.getById(request.getToeicQuestionId()));
        this.toeicAnswerChoiceRepository.save(toeicAnswerChoiceEntity);
        this.syncToeicTestService.updateToeicAnswerChoiceEntity(toeicAnswerChoiceEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Cập nhật dữ liệu thành công");
        response.setData(toeicAnswerChoiceEntity);
        return response;
    }

    @Override
    public BaseResponse deleteToeicAnswerChoiceEntity(Integer id) {
        if (!this.toeicAnswerChoiceRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic answer choice với id = " + id);
            return response;
        }
        ToeicAnswerChoiceEntity toeicAnswerChoiceEntity = this.toeicAnswerChoiceRepository.getById(id);
        this.toeicAnswerChoiceRepository.delete(toeicAnswerChoiceEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Xóa dữ liệu thành công");
        return response;
    }
}
