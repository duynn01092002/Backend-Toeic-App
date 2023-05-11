package com.hcmute.backendtoeicapp.dto.android.tests;

import com.hcmute.backendtoeicapp.entities.ToeicItemContentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AndroidItemContent {
    private Integer storageServerId;

    private String contentType;

    private String rawContent;
}
