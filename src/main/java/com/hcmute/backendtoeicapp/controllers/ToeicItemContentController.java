package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.CreateToeicItemContentRequest;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.UpdateToeicItemContentRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicItemContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/toeic-item-content")
public class ToeicItemContentController {
    @Autowired
    private ToeicItemContentService toeicItemContentService;

    @GetMapping("")
    public BaseResponse getAllToeicItemContent() {
        BaseResponse response = this.toeicItemContentService.getAllToeicItemContents();
        return response;
    }

    @GetMapping("{id}")
    public BaseResponse getToeicItemContentById(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicItemContentService.getToeicItemContentById(id);
        return response;
    }

    @PostMapping("")
    public BaseResponse createToeicItemContent(
            @RequestBody CreateToeicItemContentRequest request) throws Exception {
        BaseResponse response = this.toeicItemContentService.createToeicItemContent(request);
        return response;
    }

    @PutMapping("{id}")
    public BaseResponse updateToeicItemContent(
            @PathVariable Integer id, @RequestBody UpdateToeicItemContentRequest request) throws Exception {
        request.setId(id);
        BaseResponse response = this.toeicItemContentService.updateToeicItemContent(request);
        return response;
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteToeicItemContent(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicItemContentService.deleteToeicItemContent(id);
        return response;
    }
}
