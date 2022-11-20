package com.shop.shop.entity;

import com.shop.shop.constant.Role;
import com.shop.shop.dto.EachUnivBoardDto;
import com.shop.shop.dto.NftMemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "each_univ_board")
//@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class EachUnivBoardEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    //@Column(name="each_board_id")
    private Long id;

    @Column(length = 10, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String univ;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private Long fileId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public EachUnivBoardEntity(Long id, String author, String title, String content,String univ) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.univ=univ;
        //this.fileId = fileId;
    }
    public static EachUnivBoardEntity createBoard(EachUnivBoardDto eachUnivBoardDto){
        EachUnivBoardEntity eachUnivBoardEntity = new EachUnivBoardEntity();
        eachUnivBoardEntity.setId(eachUnivBoardDto.getId());
        eachUnivBoardEntity.setAuthor(eachUnivBoardDto.getAuthor());
        eachUnivBoardEntity.setContent(eachUnivBoardDto.getContent());
        eachUnivBoardEntity.setTitle(eachUnivBoardEntity.getTitle());
        eachUnivBoardEntity.setUniv(eachUnivBoardEntity.getUniv());
        //eachUnivBoardEntity.setFileId(eachUnivBoardEntity.getFileId());
        //eachUnivBoardEntity.setCreatedDate(eachUnivBoardEntity.getCreatedDate());
        //eachUnivBoardEntity.setModifiedDate(eachUnivBoardEntity.getModifiedDate());
        return eachUnivBoardEntity;
    }
}
