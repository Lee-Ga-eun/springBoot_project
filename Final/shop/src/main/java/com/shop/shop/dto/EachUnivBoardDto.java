package com.shop.shop.dto;

import com.shop.shop.entity.EachUnivBoardEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EachUnivBoardDto {
    private Long id;
    private String author;
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    private String content;

    private String univ;
    private Long fileId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public EachUnivBoardEntity toEntity() {
        EachUnivBoardEntity eachUnivBoardEntity = EachUnivBoardEntity.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .univ(univ)
                //.fileId(fileId)
                .build();
        return eachUnivBoardEntity;
    }

    @Builder
    public EachUnivBoardDto(String univ,Long id, String author, String title, String content, Long fileId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.univ=univ;
    }
}
