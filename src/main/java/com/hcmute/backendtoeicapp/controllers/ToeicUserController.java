package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicUser.ToeicUserSignupRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/auth")
public class ToeicUserController {
    @Autowired
    private ToeicUserService toeicUserService;

    @PostMapping("signup")
    public BaseResponse userSignup(@RequestBody ToeicUserSignupRequest request) {
        BaseResponse response = this.toeicUserService.userSignup(request);
        return response;
    }
}
