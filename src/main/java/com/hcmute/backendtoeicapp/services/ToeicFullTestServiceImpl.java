package com.hcmute.backendtoeicapp.services;


import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicFullTest.CreateToeicFullTestRequest;
import com.hcmute.backendtoeicapp.dto.toeicFullTest.UpdateToeicFullTestRequest;
import com.hcmute.backendtoeicapp.entities.ToeicFullTestEntity;
import com.hcmute.backendtoeicapp.entities.ToeicPartEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicFullTestRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicPartRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicFullTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class ToeicFullTestServiceImpl implements ToeicFullTestService {
    @Autowired
    private ToeicFullTestRepository toeicFullTestRepository;

    @Autowired
    private ToeicPartRepository toeicPartRepository;

    @Autowired
    private SyncToeicTestService syncToeicTestService;
    @Override
    public BaseResponse createToeicFullTest(CreateToeicFullTestRequest request) {
        ToeicFullTestEntity toeicFullTestEntity = new ToeicFullTestEntity();

        toeicFullTestEntity.setFullName(request.getFullName());
        toeicFullTestEntity.setSlug(toSlug(request.getFullName()));
        this.toeicFullTestRepository.save(toeicFullTestEntity);

        for (int i = 1;i <= 7;i++) {
            ToeicPartEntity toeicPartEntity = new ToeicPartEntity();
            toeicPartEntity.setPartNumber(i);
            toeicPartEntity.setToeicFullTestEntity(toeicFullTestEntity);
            this.toeicPartRepository.save(toeicPartEntity);
        }
        this.syncToeicTestService.updateToeicFullTestEntity(toeicFullTestEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo dữ liệu thành công");
        response.setData(toeicFullTestEntity);
        return response;
    }

    @Override
    public BaseResponse findAllToeicFullTests() {
        List<ToeicFullTestEntity> toeicFullTestEntities = this.toeicFullTestRepository.findAll();

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicFullTestEntities);
        return response;
    }

    @Override
    public BaseResponse findToeicFullTestById(Integer id) {
        if (!this.toeicFullTestRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic full test với id = " + id);
            return response;
        }
        ToeicFullTestEntity toeicFullTestEntity = this.toeicFullTestRepository.getById(id);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(toeicFullTestEntity);
        return response;
    }

    @Override
    public BaseResponse updateToeicFullTestById(UpdateToeicFullTestRequest request) {
        if (!this.toeicFullTestRepository.existsById(request.getId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic full test với id = " + request.getId());
            return response;
        }
        ToeicFullTestEntity toeicFullTestEntity = this.toeicFullTestRepository.getById(request.getId());
        toeicFullTestEntity.setFullName(request.getFullName());
        toeicFullTestEntity.setSlug(toSlug(request.getFullName()));

        this.toeicFullTestRepository.save(toeicFullTestEntity);
        this.syncToeicTestService.updateToeicFullTestEntity(toeicFullTestEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Cập nhật dữ liệu thành công");
        response.setData(toeicFullTestEntity);
        return response;
    }
    @Override
    public BaseResponse deleteToeicFullTestById(Integer id) {
        if(!this.toeicFullTestRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic full test với id = " + id);
            return response;
        }
        ToeicFullTestEntity toeicFullTestEntity = this.toeicFullTestRepository.getById(id);
        this.toeicFullTestRepository.delete(toeicFullTestEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Xóa dữ liệu thành công");
        return response;
    }
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
