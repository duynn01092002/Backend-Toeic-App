package com.hcmute.backendtoeicapp.dto.favoriteVocabGroup;

import com.hcmute.backendtoeicapp.dto.favoriteVocab.FavoriteVocabResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BackupFavoriteVocabGroupResponse {
    private String groupName;
    private List<FavoriteVocabResponse> favoriteVocabs = new ArrayList<>();
}
