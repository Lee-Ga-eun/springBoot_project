package com.shop.shop.dto;

import com.shop.shop.entity.NftFileEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NftFileDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public NftFileEntity toEntity() {
        NftFileEntity nftFileEntity = NftFileEntity.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return nftFileEntity;
    }

    @Builder
    public NftFileDto(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
