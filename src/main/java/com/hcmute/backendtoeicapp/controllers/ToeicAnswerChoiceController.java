package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.CreateToeicAnswerChoiceRequest;
import com.hcmute.backendtoeicapp.dto.toeicAnswerChoice.UpdateToeicAnswerChoiceRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicAnswerChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/toeic-answer-choice")
public class ToeicAnswerChoiceController {
    @Autowired
    private ToeicAnswerChoiceService toeicAnswerChoiceService;

    @GetMapping("")
    public BaseResponse getAllToeicAnswerChoices(){
        BaseResponse response = this.toeicAnswerChoiceService.getAllToeicAnswerChoices();
        return response;
    }

    @GetMapping("{id}")
    public BaseResponse getToeicAnswerChoiceById(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicAnswerChoiceService.getToeicAnswerChoiceById(id);
        return response;
    }

    @PostMapping("")
    public BaseResponse createToeicAnswerChoice(
            @RequestBody CreateToeicAnswerChoiceRequest request) {
        BaseResponse response = this.toeicAnswerChoiceService.createToeicAnswerChoice(request);
        return response;
    }

    @PutMapping("{id}")
    public BaseResponse updateToeicAnswerChoice(
            @PathVariable Integer id, @RequestBody UpdateToeicAnswerChoiceRequest request) {
        request.setId(id);
        BaseResponse response = this.toeicAnswerChoiceService.updateToeicAnswerChoice(request);
        return response;
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteToeicAnswerChoice(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicAnswerChoiceService.deleteToeicAnswerChoiceEntity(id);
        return response;
    }
}
