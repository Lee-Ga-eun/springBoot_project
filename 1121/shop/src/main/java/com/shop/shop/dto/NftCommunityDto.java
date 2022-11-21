package com.shop.shop.dto;

import com.shop.shop.entity.AllBoardEntity;
import com.shop.shop.entity.NftCommunityEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class NftCommunityDto {
    private Long id;
    private String writer;

    private String univ;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "작성된 내용이 없습니다")
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private Long fileId;

    public NftCommunityEntity toEntity(){
        NftCommunityEntity boardEntity =NftCommunityEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .fileId(fileId)
                .univ(univ)
                .build();
        return boardEntity;
    }

    @Builder
    public NftCommunityDto(String univ,Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate, Long fileId) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.fileId=fileId;
        this.univ=univ;
    }


    @Builder
    public NftCommunityDto(Long id) {
        this.id = id;

    }
}