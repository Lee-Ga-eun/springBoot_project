package com.shop.shop.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="my_univ_board")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class MyUnivBoardEntity extends TimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private NftMember nftMember; // 한 회원은 여러 개의 글을 작성할 수 있다

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public MyUnivBoardEntity(Long id, NftMember nftMember, String title, String content) {
        this.id = id;
        //this.writer = writer;
        this.nftMember = nftMember;
        this.title = title;

    }
}