package com.hcmute.backendtoeicapp.dto.android.tests;

import com.hcmute.backendtoeicapp.entities.ToeicFullTestEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AndroidToeicFullTest {
    private Integer serverId;

    private String fullName;

    private List<AndroidToeicPart> parts;

    public AndroidToeicFullTest(ToeicFullTestEntity entity) {
        this.serverId = entity.getId();
        this.fullName = entity.getFullName();
    }
}
