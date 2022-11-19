package com.shop.shop.dto;

import com.shop.shop.entity.MyUnivBoardEntity;
import com.shop.shop.entity.NftMember;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyUnivBoardDto {
    private Long id; //primary key
    private NftMember nftMember; // foreign key
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MyUnivBoardEntity toEntity(){
        MyUnivBoardEntity myUnivBoardEntity =MyUnivBoardEntity.builder()
                .id(id)
                .nftMember(nftMember)
                .title(title)
                .content(content)
                .build();
        return myUnivBoardEntity;
    }

    @Builder
    public MyUnivBoardDto(Long id, String title, String content, NftMember nftMember, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.nftMember=nftMember;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
