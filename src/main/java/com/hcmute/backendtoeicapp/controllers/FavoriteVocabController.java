package com.hcmute.backendtoeicapp.controllers;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.favoriteVocab.BackupFavoriteVocabRequest;
import com.hcmute.backendtoeicapp.dto.favoriteVocab.RestoreFavoriteVocabRequest;
import com.hcmute.backendtoeicapp.services.interfaces.FavoriteVocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/android/favorite-vocab")
public class FavoriteVocabController {
    @Autowired
    private FavoriteVocabService favoriteVocabService;

    @PostMapping("restore")
    public BaseResponse restoreFavoriteVocab(@RequestBody RestoreFavoriteVocabRequest request) {
        BaseResponse response = this.favoriteVocabService.restoreFavoriteVocab(request);
        return response;
    }

    @PostMapping("backup")
    public BaseResponse backupFavoriteVocab(@RequestBody BackupFavoriteVocabRequest request) {
        BaseResponse response = this.favoriteVocabService.backupFavoriteVocab(request);
        return response;
    }
}
