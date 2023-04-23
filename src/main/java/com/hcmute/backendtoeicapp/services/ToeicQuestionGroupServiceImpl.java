package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicPart.UpdateToeicPartRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestionGroup.CreateToeicQuestionGroupRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestionGroup.UpdateToeicQuestionGroupRequest;
import com.hcmute.backendtoeicapp.entities.ToeicPartEntity;
import com.hcmute.backendtoeicapp.entities.ToeicQuestionGroupEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicPartRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicQuestionGroupRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicQuestionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToeicQuestionGroupServiceImpl implements ToeicQuestionGroupService {
    @Autowired
    private ToeicQuestionGroupRepository toeicQuestionGroupRepository;

    @Autowired
    private ToeicPartRepository toeicPartRepository;
    @Override
    public BaseResponse createToeicQuestionGroup(CreateToeicQuestionGroupRequest request) {
        if (!this.toeicPartRepository.existsById(request.getToeicPartId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại part Id = " + request.getToeicPartId());
            return response;
        }

        ToeicQuestionGroupEntity toeicQuestionGroupEntity = new ToeicQuestionGroupEntity();
        ToeicPartEntity toeicPartEntity = this.toeicPartRepository.getById(request.getToeicPartId());
        toeicQuestionGroupEntity.setToeicPartEntity(toeicPartEntity);

        this.toeicQuestionGroupRepository.save(toeicQuestionGroupEntity);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo dữ liệu thành công");
        response.setData(toeicQuestionGroupEntity);
        return response;
    }

    @Override
    public BaseResponse getAllToeicQuestiongroup() {
        List<ToeicQuestionGroupEntity> toeicQuestionGroupEntityList = this.toeicQuestionGroupRepository.findAll();

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicQuestionGroupEntityList);;

        return  response;
    }

    @Override
    public BaseResponse getToeicQuestionGroupById(Integer id) {
        if (!toeicQuestionGroupRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại Toeic question group với id = " + id);
            return  response;
        }

        ToeicQuestionGroupEntity toeicQuestionGroupEntity = this.toeicQuestionGroupRepository.getById(id);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicQuestionGroupEntity);
        return  response;
    }

    @Override
    public BaseResponse getToeicQuestionGroupsByPartId(Integer id) {
        if (!this.toeicPartRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy part nào với id là " + id);
        }

        final List<ToeicQuestionGroupEntity> result = this.toeicQuestionGroupRepository.getToeicQuestionGroupEntitiesByToeicPartEntity(id);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(result);

        return response;
    }

    @Override
    public BaseResponse updateToeicQuestionById(Integer id, UpdateToeicQuestionGroupRequest request) {
        if (!this.toeicQuestionGroupRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại Toeic question group với id = " + id);
            return response;
        }

        if (!this.toeicPartRepository.existsById(request.getToeicPartId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại Toeic nào với id = " + request.getToeicPartId());
            return  response;
        }

        ToeicQuestionGroupEntity toeicQuestionGroupEntity = this.toeicQuestionGroupRepository.getById(id);
        ToeicPartEntity toeicPartEntity = this.toeicPartRepository.getById(request.getToeicPartId());
        toeicQuestionGroupEntity.setToeicPartEntity(toeicPartEntity);
        this.toeicQuestionGroupRepository.save(toeicQuestionGroupEntity);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Cập nhật dữ liệu thành công");
        response.setData(toeicQuestionGroupEntity);

        return  response;
    }

    @Override
    public BaseResponse deleteToeicQuestionById(Integer id) {
        if (!this.toeicQuestionGroupRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại Toeic question group với id = " + id);
            return  response;
        }

        ToeicQuestionGroupEntity toeicQuestionGroupEntity = this.toeicQuestionGroupRepository.getById(id);
        this.toeicQuestionGroupRepository.delete(toeicQuestionGroupEntity);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Xóa dữ liệu thành công");
        return  response;
    }
}
