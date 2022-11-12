package com.shop.shop.repository;

import com.shop.shop.entity.Member;
import com.shop.shop.entity.Univlist;
import org.springframework.data.jpa.repository.JpaRepository;

//findAll는 따로 만들지 않아도 기본적으로 있음
public interface UnivListRepository extends JpaRepository<Univlist, Long> {
    //Member findByEmail(String email);
}
