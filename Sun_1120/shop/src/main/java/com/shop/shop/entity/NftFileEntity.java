package com.shop.shop.entity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name="nft_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftFileEntity {

    @Id
    @GeneratedValue
    @Column(name = "nft_id")
    private Long id;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public NftFileEntity(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}