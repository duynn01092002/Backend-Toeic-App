package com.hcmute.backendtoeicapp.dto.android.tests;

import com.hcmute.backendtoeicapp.entities.ToeicPartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AndroidToeicPart {
    private Integer serverId;

    private Integer partNumber;

    private List<AndroidToeicQuestionGroup> groups;

    public AndroidToeicPart(ToeicPartEntity entity) {
        this.serverId = entity.getId();
        this.partNumber = entity.getPartNumber();
    }
}
