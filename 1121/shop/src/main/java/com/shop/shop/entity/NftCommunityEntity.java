package com.shop.shop.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "nft_each_board")
public class NftCommunityEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //IDENTITY로 했더니 HY0000에러(1364)가 나왔다, AUTO로 변경해서 해결했다
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String univ;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private Long fileId;

    @Builder
    public NftCommunityEntity(String univ, Long id, String title, String content, String writer, Long fileId) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.fileId=fileId;
        this.univ=univ;
    }

    @Builder
    public NftCommunityEntity(Long id) {
        this.id = id;
    }
}