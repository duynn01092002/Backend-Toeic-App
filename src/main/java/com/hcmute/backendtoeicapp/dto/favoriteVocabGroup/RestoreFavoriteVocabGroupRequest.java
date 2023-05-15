package com.hcmute.backendtoeicapp.dto.favoriteVocabGroup;

import com.hcmute.backendtoeicapp.dto.favoriteVocab.FavoriteVocabRequest;
import com.hcmute.backendtoeicapp.entities.FavoriteVocabEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestoreFavoriteVocabGroupRequest {
    private String groupName;
    private List<FavoriteVocabRequest> favoriteVocabs = new ArrayList<>();
}
