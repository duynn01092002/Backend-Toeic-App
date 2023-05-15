package com.hcmute.backendtoeicapp.dto.favoriteVocab;

import com.hcmute.backendtoeicapp.dto.favoriteVocabGroup.BackupFavoriteVocabGroupResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BackupFavoriteVocabResponse {
    private List<BackupFavoriteVocabGroupResponse> groups = new ArrayList<>();
}
