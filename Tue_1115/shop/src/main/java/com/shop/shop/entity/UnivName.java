package com.shop.shop.entity;


//import com.shop.shop.constant.ItemSellStatus;
//import com.shop.shop.dto.UnivListDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="univlist_nft")
@Getter
@Setter
@ToString
public class UnivName {

    @Id // 기본키(Primary Key) _ Entity 클래스는 반드시 기본키를 가짐
    @Column(name="univ_id") //name은 필드 name과 매핑. 이게 없으면 id와 연결되거나 item_id가 연결됨.(테이블명_id)(컬럼명이 id가 되면 문제가 생길 수 있음.)
    @GeneratedValue(strategy = GenerationType.AUTO)  //AutoIncrement(GenerationType.Auto는 JPA에서 관리)
    private Long id;


    @Column(name="name", nullable = false)
    private String name; //가격//name설정 안 하면, price라는 컬럼이 생기고 매핑될 것임
}

