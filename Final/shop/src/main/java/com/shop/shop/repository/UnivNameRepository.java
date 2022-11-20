package com.shop.shop.repository;

import com.shop.shop.entity.UnivName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnivNameRepository extends JpaRepository<UnivName, Long> {
//    findAll은 기본적으로 들어있다

//    findByName
    //@Query("select name from UnivName")
    List<UnivName> findByName(String name);

    List<UnivName> findAll();
}
