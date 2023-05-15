package com.hcmute.backendtoeicapp.services.interfaces;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.dto.favoriteVocab.BackupFavoriteVocabRequest;
import com.hcmute.backendtoeicapp.dto.favoriteVocab.RestoreFavoriteVocabRequest;

public interface FavoriteVocabService {
    BaseResponse restoreFavoriteVocab(RestoreFavoriteVocabRequest request);

    BaseResponse backupFavoriteVocab(BackupFavoriteVocabRequest request);
}
