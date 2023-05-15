package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.favoriteVocab.*;
import com.hcmute.backendtoeicapp.dto.favoriteVocabGroup.BackupFavoriteVocabGroupResponse;
import com.hcmute.backendtoeicapp.dto.favoriteVocabGroup.RestoreFavoriteVocabGroupRequest;
import com.hcmute.backendtoeicapp.entities.FavoriteVocabEntity;
import com.hcmute.backendtoeicapp.entities.FavoriteVocabGroupEntity;
import com.hcmute.backendtoeicapp.entities.ToeicUserEntity;
import com.hcmute.backendtoeicapp.repositories.FavoriteVocabGroupRepository;
import com.hcmute.backendtoeicapp.repositories.FavoriteVocabRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicUserRepository;
import com.hcmute.backendtoeicapp.services.interfaces.FavoriteVocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteVocabServiceImpl implements FavoriteVocabService {
    @Autowired
    private ToeicUserRepository toeicUserRepository;

    @Autowired
    private FavoriteVocabRepository favoriteVocabRepository;

    @Autowired
    private FavoriteVocabGroupRepository favoriteVocabGroupRepository;

    @Override
    public BaseResponse restoreFavoriteVocab(RestoreFavoriteVocabRequest request) {
        if (!this.toeicUserRepository.existsByGmail(request.getGmail())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại gmail: "+ request.getGmail());
            return response;
        }
        String gmail = request.getGmail();

        List<FavoriteVocabGroupEntity> favoriteVocabGroupEntities =
                this.favoriteVocabGroupRepository.getVocabGroupsByGmail(gmail);

        if (!favoriteVocabGroupEntities.isEmpty()) {
            for (FavoriteVocabGroupEntity vocabGroupEntity : favoriteVocabGroupEntities)
                this.favoriteVocabGroupRepository.deleteById(vocabGroupEntity.getId());
        }

        List<RestoreFavoriteVocabGroupRequest> groups = request.getGroups();
        for (RestoreFavoriteVocabGroupRequest group : groups) {
            if (this.favoriteVocabGroupRepository.existsByGroupNameAndGmail(group.getGroupName(), gmail)) {
                ErrorResponse response = new ErrorResponse();
                response.setMessage("Tồn tại group name: " + group.getGroupName());
                return response;
            }
            ToeicUserEntity toeicUserEntity = this.toeicUserRepository.getByGmail(request.getGmail());
            FavoriteVocabGroupEntity entity = new FavoriteVocabGroupEntity();
            entity.setGroupName(group.getGroupName());
            entity.setToeicUserEntity(toeicUserEntity);
            this.favoriteVocabGroupRepository.save(entity);

            List<FavoriteVocabRequest> favoriteVocabRequests = group.getFavoriteVocabs();
            for (FavoriteVocabRequest favoriteVocab : favoriteVocabRequests) {
                FavoriteVocabEntity vocabEntity = new FavoriteVocabEntity();
                vocabEntity.setFavoriteVocabGroupEntity(entity);
                vocabEntity.setWord(favoriteVocab.getWord());
                vocabEntity.setMeaning(favoriteVocab.getMeaning());
                this.favoriteVocabRepository.save(vocabEntity);
            }
        }

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Restore dữ liệu thành công!!");
        return response;
    }

    @Override
    public BaseResponse backupFavoriteVocab(BackupFavoriteVocabRequest request) {
        String gmail = request.getGmail();

        List<FavoriteVocabGroupEntity> groups = this.favoriteVocabGroupRepository.getVocabGroupsByGmail(gmail);
        List<BackupFavoriteVocabGroupResponse> groupsResponse = new ArrayList<>();
        for (FavoriteVocabGroupEntity group : groups) {
            BackupFavoriteVocabGroupResponse groupResponse = new BackupFavoriteVocabGroupResponse();
            List<FavoriteVocabEntity> favoriteVocabEntityList =
                    this.favoriteVocabRepository.getAllFavoriteVocabByGroupId(group.getId());
            List<FavoriteVocabResponse> vocabResponses = new ArrayList<>();
            for (FavoriteVocabEntity vocabEntity : favoriteVocabEntityList) {
                FavoriteVocabResponse vocabResponse = new FavoriteVocabResponse();
                vocabResponse.setWord(vocabEntity.getWord());
                vocabResponse.setMeaning(vocabEntity.getMeaning());
                vocabResponses.add(vocabResponse);
            }
             groupResponse.setGroupName(group.getGroupName());
            groupResponse.setFavoriteVocabs(vocabResponses);
            groupsResponse.add(groupResponse);
        }
        BackupFavoriteVocabResponse backupFavoriteVocabResponse = new BackupFavoriteVocabResponse();
        backupFavoriteVocabResponse.setGroups(groupsResponse);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setData(backupFavoriteVocabResponse);
        response.setMessage("Backup dữ liệu thành công!!");
        return response;
    }
}
