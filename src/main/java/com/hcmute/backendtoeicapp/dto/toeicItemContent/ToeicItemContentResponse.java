package com.hcmute.backendtoeicapp.dto.toeicItemContent;

import com.hcmute.backendtoeicapp.entities.ToeicItemContentEntity;
import lombok.Data;

@Data
public class ToeicItemContentResponse {
    private Integer id;
    private String type;
    private String content;
    private Integer storageId;
    private String storageViewUrl;

    public ToeicItemContentResponse() {

    }

    public ToeicItemContentResponse(ToeicItemContentEntity entity) {
        this.id = entity.getId();
        this.type = entity.getContentType();
        this.content = entity.getContent();
        if (entity.getToeicStorageEntity() != null) {
            this.storageId = entity.getToeicStorageEntity().getId();
            this.storageViewUrl = "/api/toeic/toeic-storage/view/" + entity.getToeicStorageEntity().getId();
        }
    }
}
