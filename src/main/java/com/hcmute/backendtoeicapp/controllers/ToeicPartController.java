package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.toeicPart.CreateToeicPartRequest;
import com.hcmute.backendtoeicapp.dto.toeicPart.UpdateToeicPartRequest;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/toeic/toeic-part")
public class ToeicPartController {
    @Autowired
    private ToeicPartService toeicPartService;

    @PostMapping("")
    public BaseResponse createToeicPart (
            @RequestBody CreateToeicPartRequest request ) {
        BaseResponse response = this.toeicPartService.createToeicPart(request);
        return response;
    }

    @GetMapping("")
    public BaseResponse getAllToeicPart() {
        BaseResponse response = this.toeicPartService.getAllToeicPart();
        return response;
    }

    @GetMapping("{id}")
    public BaseResponse getToeicPartById(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicPartService.getToeicPartById(id);
        return response;
    }

    @PutMapping("{id}")
    public BaseResponse updateToeicPart (
            @PathVariable Integer id,
            @RequestBody UpdateToeicPartRequest request) {
        BaseResponse response = this.toeicPartService.updateToeicPartById(id, request);
        return response;
    }

    @DeleteMapping("{id}")
    public BaseResponse deleteToeicPart(
            @PathVariable Integer id) {
        BaseResponse response = this.toeicPartService.deleteToeicPartById(id);
        return response;
    }
}
