package com.hcmute.backendtoeicapp.dto.favoriteVocab;

import com.hcmute.backendtoeicapp.dto.favoriteVocabGroup.RestoreFavoriteVocabGroupRequest;
import com.hcmute.backendtoeicapp.entities.FavoriteVocabGroupEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestoreFavoriteVocabRequest {
    private String gmail;
    private List<RestoreFavoriteVocabGroupRequest> groups = new ArrayList<>();
}
