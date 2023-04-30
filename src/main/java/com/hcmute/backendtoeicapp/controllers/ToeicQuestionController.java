package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicNewQuestion.CreateNewQuestionRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.CreateToeicQuestionRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestion.UpdateToeicQuestionRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/toeic-question")
public class ToeicQuestionController {
    @Autowired
    private ToeicQuestionService toeicQuestionService;

    @GetMapping("{id}")
    public BaseResponse getToeicQuestionById(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicQuestionService.getToeicQuestionById(id);
        return response;
    }

    @GetMapping("")
    public BaseResponse getAllToeicQuestions() {
        BaseResponse response = this.toeicQuestionService.getAllToeicQuestions();
        return response;
    }

    @GetMapping("get-questions-by-group/{id}")
    public BaseResponse getQuestionsByGroupId(
            @PathVariable Integer id
    ) {
        BaseResponse response = this.toeicQuestionService.getToeicQuestionsByGroupId(id);
        return response;
    }

    @PostMapping("")
    public BaseResponse createToeicQuestion(@RequestBody CreateToeicQuestionRequest request) {
        BaseResponse response = this.toeicQuestionService.createToeicQuestion(request);
        return response;
    }

    @PutMapping("{id}")
    public BaseResponse updateToeicQuestion(
            @PathVariable Integer id,
            @RequestBody UpdateToeicQuestionRequest request) {
        request.setId(id);
        BaseResponse response = this.toeicQuestionService.updateToeicQuestion(request);
        return response;
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteToeicQuestion(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicQuestionService.deleteToeicQuestion(id);
        return response;
    }

    @PostMapping("create-new-question")
    @Transactional
    public BaseResponse createNewQuestion(@RequestBody CreateNewQuestionRequest request) {
        BaseResponse response = this.toeicQuestionService.createNewQuestion(request);
        return response;
    }
}
