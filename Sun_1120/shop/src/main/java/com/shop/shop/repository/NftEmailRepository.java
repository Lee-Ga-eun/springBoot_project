package com.shop.shop.repository;

import com.shop.shop.entity.NftMember;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface NftEmailRepository extends JpaRepository<NftMember, Long> {
    List<NftMember> findByEmail(String email);
}