package com.shop.shop.entity;

import com.shop.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //JPA에게 엔티티 클래스라는 것을 알려줌.
@Table(name="item")
@Getter
@Setter
@ToString // 필드에 있는 모든 값들을 모아서 한꺼번에 로그 확인 가능
// Item(id=1, itemNm=좋은 상품, price=10000, stockNumber=100, itemDetail=좋은 상품 설명, itemSellStatus=SELL, regTime=2022-09-28T14:51:31.

public class Item {
    @Id // 기본키(Primary Key) _ Entity 클래스는 반드시 기본키를 가짐
    //Column 어노테이션의 추가 설정: name, nullable, unique, insertable 등
    @Column(name="item_id") //name은 필드 name과 매핑. 이게 없으면 id와 연결되거나 item_id가 연결됨.(테이블명_id)(컬럼명이 id가 되면 문제가 생길 수 있음.)
    //자바에선 낙타, db에선 스네이크 **
    //GeneratedValue: 기본키를 생성하는 방법
    @GeneratedValue(strategy = GenerationType.AUTO)  //AutoIncrement(GenerationType.Auto는 JPA에서 관리)
    // GenerationType.AUTO: 데이터베이스에 의존하지 않고 기본키를 할당하는 방법으로 JPA 구현체가 자동으로 생성 전략을 결정한다
    private Long id; //상품코드//item_id 필드와 매핑됨

    @Column(nullable=false, length=50) //itemNm과 연결 (name 설정 안 했으니까)
    private String itemNm; // 상품명 , name 지정 안 함: item_nm으로 연결
    //item_nm varchar(50) not null


    @Column(name="price", nullable = false)
    private int price; //가격//name설정 안 하면, price라는 컬럼이 생기고 매핑될 것임

    @Column(nullable=false)
    private int stockNumber; //재고량 //stockNumber 컬럼 생길 것임: stock_number

    @Lob //txt(아주 긴 문장)를 넣을 수 있다
    @Column(nullable = false)
    private String itemDetail; //제품 상세 설명
    //item_detail longtext not null

   //Enum: 자바에서 string 표현.
    @Enumerated(EnumType.STRING)  // enum 타입을 매핑하기 위함
    //변수로 상수로 만든 이유: 다른 값들이 안 들어가게 제한하기 위함
    private ItemSellStatus itemSellStatus; //클래스 타입이 아님. enum 타입임
    //item_sell_status varchar(255)
    private LocalDateTime regTime; //registerTime: 등록시간
    // reg_time datetime(6)
    private LocalDateTime updateTime; // 수정 시간
    // update_time datetime(6)
}
