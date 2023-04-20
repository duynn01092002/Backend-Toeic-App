package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicPart.CreateToeicPartRequest;
import com.hcmute.backendtoeicapp.dto.toeicPart.UpdateToeicPartRequest;
import com.hcmute.backendtoeicapp.entities.ToeicFullTestEntity;
import com.hcmute.backendtoeicapp.entities.ToeicPartEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicFullTestRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicPartRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToeicPartServiceImpl implements ToeicPartService {
    @Autowired
    private ToeicFullTestRepository toeicFullTestRepository;

    @Autowired
    private ToeicPartRepository toeicPartRepository;

    @Override
    public BaseResponse createToeicPart(CreateToeicPartRequest request) {
        if (request.getPartNumber() < 1 || request.getPartNumber() > 7) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại part id = " + request.getPartNumber());
            return response;
        }

        if (!this.toeicFullTestRepository.existsById(request.getToeicFullTest())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại full test id = " + request.getToeicFullTest());
            return response;
        }

        if (this.toeicPartRepository.existPartInFullTest(request.getPartNumber(), request.getToeicFullTest())){
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Đã tồn tại part " + request.getPartNumber() + " trong test " + request.getToeicFullTest());
            return response;
        }

        ToeicPartEntity toeicPartEntity = new ToeicPartEntity();
        toeicPartEntity.setPartNumber(request.getPartNumber());

        ToeicFullTestEntity toeicFullTestEntity = this.toeicFullTestRepository.getById(request.getToeicFullTest());
        toeicPartEntity.setToeicFullTestEntity(toeicFullTestEntity);

        this.toeicPartRepository.save(toeicPartEntity);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo dữ liệu thành công");
        response.setData(toeicPartEntity);
        return response;
    }

    @Override
    public BaseResponse updateToeicPartById(Integer id, UpdateToeicPartRequest request) {
        if(!this.toeicPartRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic part với id = " + id);
            return response;
        }

        if (request.getPartNumber() < 1 || request.getPartNumber() > 7) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại part id = " + request.getPartNumber());
            return response;
        }

        if (this.toeicPartRepository.existPartInFullTest(request.getPartNumber(), request.getToeicFullTest())){
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Đã tồn tại part " + request.getPartNumber() + " trong test " + request.getToeicFullTest());
            return response;
        }

        ToeicPartEntity toeicPartEntity = this.toeicPartRepository.getById(id);
        toeicPartEntity.setPartNumber(request.getPartNumber());

        ToeicFullTestEntity toeicFullTestEntity = this.toeicFullTestRepository.getById(request.getToeicFullTest());
        toeicPartEntity.setToeicFullTestEntity(toeicFullTestEntity);

        this.toeicPartRepository.save(toeicPartEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Cập nhật dữ liệu thành công");
        response.setData(toeicPartEntity);
        return response;
    }

    @Override
    public BaseResponse getToeicPartById(Integer id) {
        if (!this.toeicPartRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic part với id = " + id);
            return response;
        }

        ToeicPartEntity toeicPartEntity = this.toeicPartRepository.getById(id);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicPartEntity);
        return response;
    }

    @Override
    public BaseResponse getAllToeicPart() {
        List<ToeicPartEntity> toeicPartEntities = this.toeicPartRepository.findAll();

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicPartEntities);
        return response;
    }

    @Override
    public BaseResponse deleteToeicPartById(Integer id) {
        if(!this.toeicPartRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic part với id = " + id);
            return response;
        }

        ToeicPartEntity toeicPartEntity = this.toeicPartRepository.getById(id);
        this.toeicPartRepository.delete(toeicPartEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Xóa dữ liệu thành công");
        return response;
    }
}
