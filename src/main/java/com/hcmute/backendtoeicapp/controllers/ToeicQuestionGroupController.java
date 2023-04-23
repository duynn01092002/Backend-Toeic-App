package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicQuestionGroup.CreateToeicQuestionGroupRequest;
import com.hcmute.backendtoeicapp.dto.toeicQuestionGroup.UpdateToeicQuestionGroupRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicQuestionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/toeic-question-group")
public class ToeicQuestionGroupController {
    @Autowired
    private ToeicQuestionGroupService toeicQuestionGroupService;

    @PostMapping("")
    public BaseResponse createToeicQuestionGroup(
            @RequestBody CreateToeicQuestionGroupRequest request ) {
        BaseResponse response = this.toeicQuestionGroupService.createToeicQuestionGroup(request);
        return  response;
    }

    @GetMapping("")
    public BaseResponse getAllToeicQuestionGroup() {
        BaseResponse response = this.toeicQuestionGroupService.getAllToeicQuestiongroup();
        return response;
    }

    @GetMapping("{id}")
    public BaseResponse getToeicQuestionGroupById(
            @PathVariable Integer id ) {
        BaseResponse response = this.toeicQuestionGroupService.getToeicQuestionGroupById(id);
        return  response;
    }

    @GetMapping("get-groups-by-part/{id}")
    public BaseResponse getToeicQuestionGroupsByPartId(
            @PathVariable Integer id
    ) {
        BaseResponse response = this.toeicQuestionGroupService.getToeicQuestionGroupsByPartId(id);
        return response;
    }

    @PutMapping("{id}")
    public BaseResponse updateToeicQuestionGroupById(
            @PathVariable Integer id,
            @RequestBody UpdateToeicQuestionGroupRequest request ) {
        BaseResponse response = this.toeicQuestionGroupService.updateToeicQuestionById(id, request);
        return  response;
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteToeicQuestionGroupByid(
            @PathVariable Integer id ) {
        BaseResponse response = this.toeicQuestionGroupService.deleteToeicQuestionById(id);
        return  response;
    }
}
