package com.shop.shop.repository;

import com.shop.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

// ItemRepository가 가져야할 엔티티가 무엇? -> Item 클래스로 설정해줌
/*
Long: 모든 테이블에는 primary key가 있다. 기본키는 row들이 중복되지 않게 하기 위해 만드는데,
한 상품이 들어갈 때마다 자동으로 들어가게 하는 게 편하다. Integer, Long 등 형을 설정하면 되는데
Item 클래스에서 id(primary key)가 Long 타입이기 때문.
 */

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    List<Item> findByItemNm(String itemNm); // find+(Entity명)+By+(변수명)으로 메소드 생성. Entity명 제거할 수 있음
    // List<Item> findItemByItemNm(String itemNm);
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    //List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); // 입력된 price 미만 값 확인 및 price 값 기준 정열
    // 왜 Int가 아닌 integer를 써야 하는가???

    // List<Item> resultItemList

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

// Query/ JPQL: 엔티티를 대상/ QueryDsl
    @Query("select i from Item i where i.itemDetail like %:zoa% order by i.price desc")
    List<Item> findByItemDetail(@Param("zoa") String zoa);

    //@Query("select i from Item i where i.itemDetail like " +
      //      "%:itemDetail% order by i.price desc")
   // List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); // 직접 Query를 입력하여 값 확인 JPQLS
// queryFactory.selectFrom(qItem).where(qItem.itemDetail.like('%'+'테스트'+'%')).orderBy(qItem.price.desc())
    // fetch(): 쿼리 결과를 리스트로 변환
    // booleanBuilder.and()
    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail); // 직
}

//itemDetail: 좋은 -> :itemDetail : 좋은, -> 우리는 Item 테이블이 아닌 소문자 item 테이블이다.