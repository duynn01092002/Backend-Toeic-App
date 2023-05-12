package com.hcmute.backendtoeicapp.dto.toeicStorage;

import lombok.Data;

import java.util.List;

@Data
public class GetListStorageEntityRequest {
    private List<Integer> storageIdList;
}
