package com.shop.shop.repository;
// 위치: test/java/com.shop.shop/repository/ItemRepositoryTest --> 각각 편하게 테스트할 수 있다는 장점 (똑같은 패키지 구조를 가짐)
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.shop.constant.ItemSellStatus;
import com.shop.shop.entity.Item;
import com.shop.shop.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
import static org.junit.jupiter.api.Assertions.*;

// 우선 test라는 db가 만들어져 있어야 한다

@SpringBootTest // 테스트 실행시 모든 Bean을 IoC 컨테이너에 등록 = 스프링부트 구동과 동일한 환경 조건
@TestPropertySource(locations="classpath:application-test.properties") // 테스트 실행시 우선되는 설정파일
class ItemRepositoryTest {
    @Autowired // 필드 Bean 주입
    ItemRepository itemRepository; // JpaRepository는 인터페이스가 준비되 기만 하면, 자동으로 Bean을 생성한다

    @Test // Method 테스트 대상 지정 <- main 어플리케이션과는 관련이 없다. 테스트 영역이다.
    @DisplayName("상품 저장 확인") // 테스트명 <- 콘솔 화면
    public void createItemTest(){ // <-- 세모 클릭: 밑에 부분 돌고 실행. application-test.properties로 연결
        Item item = new Item(); //item 엔티티 객체화
        item.setItemNm("좋은 상품");
        item.setPrice(10000);
        item.setItemDetail("좋은 상품 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item); // 실제 save의 구현체: 아이템 팩토리 - 아이템 가져옴 - persist - ....
        // save를 통해 db에 들어감
        System.out.println(savedItem.toString());
        // itemRepository가 잘 설계됐는지, 잘 동작하는지 & Item 엔티티가 잘 설계됐는지, 잘 동작하는지를 테스트 완료
    }
    @Test
    public void createItemList(){
        for(int i=1; i<=10; i++){
            Item item = new Item();
            item.setItemNm("좋은 상품 " + i);
            item.setPrice(10000 + i);
            item.setItemDetail("좋은 상품 설명 " + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100); item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem=itemRepository.save(item); // save: 저장. 기본적으로 가지고 있음 (메소드 만들지 않아도 됨)
            // <save> 영속성 관점에서 보면, save를 하면서 영속성 컨테이너에 데이터를 db에 날리고, save가 끝난 후 값을 리턴(select)해준다
            //
            System.out.println(savedItem.toString());
        }

    }

    @Test // 테스트 메소드라는 것을 인식하고 테스트를 진행한다 (세모가 뜨는 이유)
    @DisplayName("상품명 조회 확인")
    public void findByItemNmTest(){
        this.createItemList(); // 호출
        List<Item> itemList = itemRepository.findByItemNm("좋은 상품 7"); // List 타입.
        // select * from item where item_name="좋은 상품 7";
        for(Item item : itemList){ // 리스트로 리턴받음
            LOGGER.info(item.toString());
            // Item(id=7, itemNm=좋은 상품 7, price=10007, stockNumber=100, itemDetail=좋은 상품 설명 7, itemSellStatus=SELL, regTi

        }
    }

    @Test
    @DisplayName("상품명 or 상품 설명 확인")
    public void findByItemOrItemDetailTest() { // where에서의 or 과 같은 것임
        // select * from item where item_nm ="~" or item_detail="~";
        // 인자값 두 개 받아야함
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("좋은 상품 1", "좋은 상품 설명 5");
        for (Item item : itemList) { //List 길이를 모르기 때문에, 리스트의 멤버가 존재하지 않을 때까지 돌게 한다
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for(Item item:itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 확인")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("좋은 상품");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 확인 NATIVE")
    public void findByItemDetailByNativeTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailByNative("좋은 상품");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @PersistenceContext // 영속성 컨텍스트 사용
    EntityManager em; // EntityManager 빈 주입

    @Test
    @DisplayName("Querydsl 조회 확인")
    public void queryDslTest(){
        this.createItemList();
        // 생성자의 파라미터로 Entity Manager 객체 넣어줌
        JPAQueryFactory queryFactory = new JPAQueryFactory(em); // JPAQueryFactory로 쿼리를 동적으로 생성
        QItem qItem = QItem.item; // Querysdl을 통해 쿼리를 생성하기 위해, QItem 객체를 사용
        JPAQuery<Item> query  = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "좋은 상품 설명" + "%"))
                .orderBy(qItem.price.desc());
        //System.out.println("eq가 뭘까 ---------");
        //System.out.println(ItemSellStatus.SELL);
        // item.itemSellStatus=SELL

        List<Item> itemList = query.fetch(); // JPAQuery 메소드 fetch를 이용해 결과 반환

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    public void createItemListPaging(){
        for(int i=1;i<=5;i++){
            Item item = new Item();
            item.setItemNm("좋은 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("좋은 상품 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }

        for(int i=6;i<=10;i++){
            Item item = new Item();
            item.setItemNm("좋은 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("좋은 상품 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 Querydsl 페이징 조회 확인")
    public void queryDslPagingTest() {

        this.createItemListPaging();

        // predicate를 BooleanBuilder로 구현함
        // Page<T> findAll(predicate, pageable);
        // Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        // prdicate: '이 조건이 맞다'고 판단하는 근거를 함수로 제공하는 것
        BooleanBuilder booleanBuilder = new BooleanBuilder(); //factory 역할을 하는 것처럼 쿼리를 만들 수 있음
        QItem item = QItem.item;

        String itemDetail = "좋은 상품 설명";
        int price = 10003;
        String itemSellStat = "SELL";
        //booleanBuilder을 통해 쿼리에 들어갈 조건을 만들어준다. queryFactory와 비교해볼 것
        // queryFactory와 달리, select할 필요는 없음

        //qitem타입이면 각각의 조건절을 다 넣을 수 있다.
        // 조건절
        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        //gt: <=
        booleanBuilder.and(item.price.gt(price));
        //booleanBuilder.or(item.)

        System.out.println(ItemSellStatus.SELL);
        //서로 문자열이 같다면, SELL을 넣어줘라
        //StringUtils를 통해 좌항 우항을 비교.
        // booleanBuilder를 통해 and 쿼리를 더 추가하여라

        // 상품의 판매 상태가 SELL일 때만, 불리언 빌더에 판매 조건을 동적으로 추가
        if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        System.out.println("불리언 빌더"+booleanBuilder);
        // booleanBuilder:
        // item.itemDetail like %좋은 상품 설명% && item.price >10003 && item.itemSellStatus=SELL


        //itemRepository는 findAll을 가지고 있음 (JPA로부터)
        // List<Item> resultItemList = (List<Item>) itemRepository.findAll(BooleanBuilder);

        // int page=1 이면, PageRequest(1-1,5로 표현해야 한다)

        // int page=1; page-=1; 이면, PageRequest.of(0,5)

        //page=2이면,,,, 6,7,8,9,10
        // limit ?, ? : 첫번째 페이지는 0에서 5페이지를 출력해라 라는 게 필요 없었지만,
        // 5포인트부터 5개를 출력해라 라고 되는 것 -> 즉, (5,5)일 것이다 즉 id는 6,7,8,9,10
        //그러니까, limit는 sql 쿼리에 대입해서 생각하면 되는데
        // page(0,5)이면 limit 0*5,5이고
        // page(1,5)이면 limit 1*5,5이다.
        // 두번째 인자는 개수이다. 출력할 개수
        // 첫번째 인자는 포인트로 생각.
        //여기서 조건에선, 애초에 price 10003 이상이면서 SELL인 게 두 개밖에 없기 때문에, limit 5,5이면 출력되는 게 없는 것이다.


        int page = 0;
        Pageable pageable = (Pageable) PageRequest.of(page, 5); //0부터 시작
        // page 선언 없이 of(0,5)이면, 2개만 출력될 것이다.
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + itemPagingResult.getTotalElements());

        //Page<Item>...이 부분을 그냥 List에 넣어주는 코드
        List<Item> resultItemList = itemPagingResult.getContent();
        for (Item resultItem : resultItemList) {
            System.out.println(resultItem.toString());
        }
    }

    @Test
    @DisplayName("상품 Querydsl 페이징 조회 확인")
    public void pageableTest() {

        this.createItemListPaging();
        BooleanBuilder booleanBuilder = new BooleanBuilder(); //factory 역할을 하는 것처럼 쿼리를 만들 수 있음
        QItem item = QItem.item;

        String itemDetail = "좋은 상품 설명";
        int price = 10006;

        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(item.price.gt(price));

        System.out.println(ItemSellStatus.SELL);

        System.out.println("불리언 빌더"+booleanBuilder);

        int page = 0;
        Pageable pageable = (Pageable) PageRequest.of(1, 3); //0부터 시작
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for (Item resultItem : resultItemList) {
            System.out.println(resultItem.toString());
        }
    }}
// page(0,5)
//    total elements : 10
//        Item(id=1, itemNm=좋은 상품1, price=10001, stockNumber=100, itemDetail=좋은 상품 설명1, itemSellStatus=SELL, regTime=2022-10-11T21:03:23.744023, updateTime=2022-10-11T21:03:23.744047)
//        Item(id=2, itemNm=좋은 상품2, price=10002, stockNumber=100, itemDetail=좋은 상품 설명2, itemSellStatus=SELL, regTime=2022-10-11T21:03:23.823717, updateTime=2022-10-11T21:03:23.823735)
//        Item(id=3, itemNm=좋은 상품3, price=10003, stockNumber=100, itemDetail=좋은 상품 설명3, itemSellStatus=SELL, regTime=2022-10-11T21:03:23.831232, updateTime=2022-10-11T21:03:23.831244)
//        Item(id=4, itemNm=좋은 상품4, price=10004, stockNumber=100, itemDetail=좋은 상품 설명4, itemSellStatus=SELL, regTime=2022-10-11T21:03:23.840420, updateTime=2022-10-11T21:03:23.840437)
//        Item(id=5, itemNm=좋은 상품5, price=10005, stockNumber=100, itemDetail=좋은 상품 설명5, itemSellStatus=SELL, regTime=2022-10-11T21:03:23.848126, updateTime=2022-10-11T21:03:23.848153)


//page(0,3)
//total elements : 10
//        Item(id=1, itemNm=좋은 상품1, price=10001, stockNumber=100, itemDetail=좋은 상품 설명1, itemSellStatus=SELL, regTime=2022-10-11T21:04:34.057762, updateTime=2022-10-11T21:04:34.057785)
//        Item(id=2, itemNm=좋은 상품2, price=10002, stockNumber=100, itemDetail=좋은 상품 설명2, itemSellStatus=SELL, regTime=2022-10-11T21:04:34.138971, updateTime=2022-10-11T21:04:34.138984)
//        Item(id=3, itemNm=좋은 상품3, price=10003, stockNumber=100, itemDetail=좋은 상품 설명3, itemSellStatus=SELL, regTime=2022-10-11T21

//page(1,5)
//total elements : 10
//        Item(id=6, itemNm=좋은 상품6, price=10006, stockNumber=0, itemDetail=좋은 상품 설명6, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:05:40.485770, updateTime=2022-10-11T21:05:40.485783)
//        Item(id=7, itemNm=좋은 상품7, price=10007, stockNumber=0, itemDetail=좋은 상품 설명7, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:05:40.492241, updateTime=2022-10-11T21:05:40.492254)
//        Item(id=8, itemNm=좋은 상품8, price=10008, stockNumber=0, itemDetail=좋은 상품 설명8, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:05:40.499298, updateTime=2022-10-11T21:05:40.499310)
//        Item(id=9, itemNm=좋은 상품9, price=10009, stockNumber=0, itemDetail=좋은 상품 설명9, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:05:40.506359, updateTime=2022-10-11T21:05:40.506372)
//        Item(id=10, itemNm=좋은 상품10, price=10010, stockNumber=0, itemDetail=좋은 상품 설명10, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:05:40.514394, updateTime=2022-10-11T21:05:40.514406)
//        2022-10-11 21:


//page(1,3)
//total elements : 10
//        Item(id=4, itemNm=좋은 상품4, price=10004, stockNumber=100, itemDetail=좋은 상품 설명4, itemSellStatus=SELL, regTime=2022-10-11T21:06:52.227895, updateTime=2022-10-11T21:06:52.227906)
//        Item(id=5, itemNm=좋은 상품5, price=10005, stockNumber=100, itemDetail=좋은 상품 설명5, itemSellStatus=SELL, regTime=2022-10-11T21:06:52.233763, updateTime=2022-10-11T21:06:52.233775)
//        Item(id=6, itemNm=좋은 상품6, price=10006, stockNumber=0, itemDetail=좋은 상품 설명6, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:06:52.239503, updateTime=2022-10-11T21:06:52.239515)
//        2022-10-1

//page(-1,5)
//java.lang.IllegalArgumentException: Page index must not be less than zero

//    int price = 10006; page(0,3)
//total elements : 4
//        Item(id=7, itemNm=좋은 상품7, price=10007, stockNumber=0, itemDetail=좋은 상품 설명7, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:12:00.608157, updateTime=2022-10-11T21:12:00.608169)
//        Item(id=8, itemNm=좋은 상품8, price=10008, stockNumber=0, itemDetail=좋은 상품 설명8, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:12:00.614234, updateTime=2022-10-11T21:12:00.614246)
//        Item(id=9, itemNm=좋은 상품9, price=10009, stockNumber=0, itemDetail=좋은 상품 설명9, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:12:00.619867, updateTime=2022-10-11T21:12:00.619879)


//int price=10006; page(1,3)
//    total elements : 4
//        Item(id=10, itemNm=좋은 상품10, price=10010, stockNumber=0, itemDetail=좋은 상품 설명10, itemSellStatus=SOLD_OUT, regTime=2022-10-11T21:13:57.793021, updateTime=2022-10-11T21:13:57.793033)
//        2022-10-11 21:13:57.906  INFO 18854