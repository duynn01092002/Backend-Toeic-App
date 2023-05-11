package com.hcmute.backendtoeicapp.dto.android.vocab;

import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import com.hcmute.backendtoeicapp.entities.ToeicVocabWordEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AndroidToeicVocabWord {
    private Integer serverId;

    private String english;

    private String vietnamese;

    private String pronounce;

    private String exampleEnglish;

    private String exampleVietnamese;

    private String audioFileName;

    private String imageFilename;

    public AndroidToeicVocabWord(
            @NonNull ToeicVocabWordEntity entity
    ) {
        this.serverId = entity.getId();
        this.english = entity.getEnglish();
        this.vietnamese = entity.getVietnamese();
        this.pronounce = entity.getPronounce();
        this.exampleEnglish = entity.getExampleEnglish();
        this.exampleVietnamese = entity.getExampleVietnamese();
    }
}
