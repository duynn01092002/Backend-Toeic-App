package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.repositories.ToeicFullTestRepository;
import com.hcmute.backendtoeicapp.services.interfaces.AndroidToeicTestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/android/toeic-test")
public class AndroidToeicTestController {
    private final AndroidToeicTestService androidToeicTestService;

    public AndroidToeicTestController(
            AndroidToeicTestService androidToeicTestService
    ) {
        this.androidToeicTestService = androidToeicTestService;
    }

    @GetMapping("get-tests-data")
    public BaseResponse getAllTestsDataJson() {
        return this.androidToeicTestService.getAllTestsData();
    }
}
