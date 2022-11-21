package com.shop.shop.repository;

import com.shop.shop.entity.NftMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NftMemberRepository extends JpaRepository<NftMember, Long> {
    NftMember findByEmail(String email); // 이메일로 접근
    //List<NftMember> findById(String id);
}
