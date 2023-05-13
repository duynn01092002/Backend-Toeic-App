package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicUser.ToeicUserSignupRequest;
import com.hcmute.backendtoeicapp.entities.ToeicUserEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicUserRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToeicUserServiceImpl implements ToeicUserService {
    @Autowired
    private ToeicUserRepository userRepository;

    @Override
    public BaseResponse userSignup(ToeicUserSignupRequest request) {
        if (this.userRepository.existsByGmail(request.getGmail())) {
            SuccessfulResponse response = new SuccessfulResponse();
            response.setMessage("Tồn tại gmail - bỏ qua");
            return response;
        }
        ToeicUserEntity userEntity = new ToeicUserEntity();
        userEntity.setFullName(request.getFullName());
        userEntity.setGmail(request.getGmail());
        this.userRepository.save(userEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Đăng ký thành công");
        response.setData(userEntity);
        return response;
    }
}
