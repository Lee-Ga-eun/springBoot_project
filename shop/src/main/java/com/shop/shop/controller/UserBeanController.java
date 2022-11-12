package com.shop.shop.controller;


import com.shop.shop.component.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserBeanController {
    @Autowired // "필드" 주입 방법
    @Qualifier("superShotGun") //component.BasicConfiguration에 있는 superShotGun 클래스 빈이 주입됨
    private Weapon swp; // 얘도 Weapon 타입 BUT @Qualifier을 통해 헷갈리지 않음

    // 얘도 Weapon 타입 + @Qualifier가 없다 ?! -> super로 지정될 것임
    private final Weapon bwp; // "생성자" 주입시 "final" 선언

    @Autowired // 빈 주입하기
    public UserBeanController(Weapon bwp) { // "생성자" 주입 방법
        // @Qualifier("basicShotGun")가 Weapon 타입의 객체의 @Primary 선언으로 생략 가능
        this.bwp = bwp; // bwp가 Weapon 타입으로, swp와 겹쳐서 어느 클래스의 빈인지 ? -> @Primary로 간다
        System.out.println(bwp); // bwp는 basic Shotgun일 것이다
    }
    /*
    @Autowired // 생성자 주입 방법
    public UserBeanController(@Qualifier("basicShotGun") Weapon bwp) {
        this.bwp = bwp;
    }
    */
    //GetMapping!!!! post/user_bean.html로 모델을 보내고, 우린 /b를 통해 결과를 확인할 수 있다!!!
    @GetMapping("/b") // Controller는 Model을 이용해 데이터를 가져오고 view에 데이터를 넘겨 view를 생성함
    public String main(Model m){
        m.addAttribute("name","홍길동");
        // addAttribute(attributeName, attributeValue): name이란 이름으로 value 객체를 추가함
        m.addAttribute("weapon", bwp.getModel());
        return "post/user_bean"; // view에 넘긴다
    }

    @GetMapping("/s")  // {"name":"홍길동,"weapon":"Super ShotGun"}
    public @ResponseBody ResponseEntity<Model> get(Model m) {
        m.addAttribute("name","홍길동");
        m.addAttribute("weapon", swp.getModel());
        return new ResponseEntity<Model>(m, HttpStatus.OK);
        // view와 매핑시키지 않음.
        // 요청 받는 응답에 매핑하기 위해 응답 결과를 body에 넣어서 보낸다 .. @responsebody
        // 반환 방식은 여러 가지가 있고 변환 시켜주는 역할을 ResponseEntity가 한다
        // ResponseEntity: 응답으로 변환될 정보를 모두 담은 요소를 객체로 만들어서 반환해줌
        // ResponseEntity<반환할 타입>

    }

    @GetMapping("/ss")
    public String html(Model m){
        m.addAttribute("name","슈퍼샷건");
        m.addAttribute("weapon",swp.getModel());
        return "post/user_bean"; // view로 넘겨주는 방법
    }
}
