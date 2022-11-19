package com.shop.shop.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component로 Bean을 '자동' 등록하기
// 스프링은 컴포넌트 스캔을 통해서 @Component가 있는 클래스를 찾고, 자동으로 Bean으로 등록해준다
@Component // 사용자 정의 빈 등록 // 클래스 위에 작성한다
@Qualifier("basicShotGun") // Bean의 구체적인 선별 방법 (추가 구분자)
@Primary // 같은 Bean 객체 주입시 '우선 순위' 지정
public class ShotGun implements Weapon {
    private String model = "Basic ShotGun";

    @Override // Weapon 인터페이스 상속
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override // Weapon 인터페이스 상속
    public void fire() {
        System.out.println(model+" fire!!");
    }
}

// @Qualifier는 Bean의 구체적인 선별 방법이다
// @Autowired @basicShotGun 이런 식으로 쓰일 것이다
// Bean 주입 시 Qualifier가 없으면, primary가 쓰인다