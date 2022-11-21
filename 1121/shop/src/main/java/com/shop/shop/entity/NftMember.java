package com.shop.shop.entity;


import com.shop.shop.constant.Role;
import com.shop.shop.dto.NftMemberDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="nft_member")
@Getter
@Setter
@ToString
public class NftMember {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String university;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static NftMember createMember(NftMemberDto nftMemberDto, PasswordEncoder passwordEncoder){
        NftMember nftMember = new NftMember();
        nftMember.setName(nftMemberDto.getName());
        nftMember.setEmail(nftMemberDto.getEmail());
        nftMember.setUniversity(nftMemberDto.getUniversity());
        String password = passwordEncoder.encode(nftMemberDto.getPassword());
        nftMember.setPassword(password);
        nftMember.setRole(Role.ADMIN);
        return nftMember;
    }
}