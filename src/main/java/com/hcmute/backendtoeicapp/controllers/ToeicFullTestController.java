package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicFullTest.CreateToeicFullTestRequest;
import com.hcmute.backendtoeicapp.dto.toeicFullTest.UpdateToeicFullTestRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicFullTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/toeic-full-test")
public class ToeicFullTestController {
    @Autowired
    private ToeicFullTestService toeicFullTestService;

    @GetMapping("{id}")
    public BaseResponse getToeicFullTest(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicFullTestService.findToeicFullTestById(id);
        return response;
    }

    @GetMapping
    public BaseResponse getAllToeicFullTests() {
        BaseResponse response = this.toeicFullTestService.findAllToeicFullTests();
        return response;
    }

    @PostMapping("")
    public BaseResponse createToeicFullTest(
            @RequestBody CreateToeicFullTestRequest request) {
        BaseResponse response = this.toeicFullTestService.createToeicFullTest(request);
        return response;
    }

    @PutMapping("{id}")
    public BaseResponse updateToeicFullTest(
            @PathVariable Integer id,
            @RequestBody UpdateToeicFullTestRequest request) {
        request.setId(id);
        BaseResponse response = this.toeicFullTestService.updateToeicFullTestById(request);
        return response;
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteToeicFulTest(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicFullTestService.deleteToeicFullTestById(id);
        return response;
    }
}
