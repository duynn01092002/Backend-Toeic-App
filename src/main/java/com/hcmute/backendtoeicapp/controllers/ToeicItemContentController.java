package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.CreateNewItemContentRequest;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.CreateToeicItemContentRequest;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.UpdateToeicItemContentRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicItemContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("get-question-contents-by-group/{id}")
    public BaseResponse getQuestionContentsByGroupId(
            @PathVariable Integer id
    ) {
        BaseResponse response = this.toeicItemContentService.getQuestionItemContentsByGroupId(id);
        return response;
    }

    @GetMapping("get-transcripts-by-group/{id}")
    public BaseResponse getTranscriptsByGroupId(
            @PathVariable Integer id
    ) {
        BaseResponse response = this.toeicItemContentService.getTranscriptContentsByGroupId(id);
        return response;
    }
    @Deprecated
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

    @PostMapping("create-new-item-content")
    public BaseResponse createNewItemContent(
            @RequestParam("contentType") String contentType,
            @RequestParam(value="stringContent",required = false) String stringContent,
            @RequestParam(value = "content",required = false) MultipartFile content,
            @RequestParam(value="questionContentId",required = false) Integer questionContentId,
            @RequestParam(value = "questionTranscriptId",required = false) Integer questionTranscriptId) throws Exception {
        CreateNewItemContentRequest request = new CreateNewItemContentRequest();
        request.setContentType(contentType);
        request.setContent(content);
        request.setStringContent(stringContent);
        request.setQuestionContentId(questionContentId);
        request.setQuestionTranscriptId(questionTranscriptId);
        BaseResponse response = this.toeicItemContentService.createNewItemContent(request);
        return response;
    }
}
