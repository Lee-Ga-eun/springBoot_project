package com.shop.shop.component;

import com.shop.shop.component.ShotGun;
import com.shop.shop.component.Weapon;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration 안에서 bean을 등록하기 (Bean 수동 등록 방법)
@Configuration // @Bean 메소드 사용을 위해 선언
public class BasicConfiguration {
    @Bean // 사용자 정의 Bean 등록
    @Qualifier("superShotGun") // @Bean(name="superShotGun") 대체 가능
    public Weapon superShotGun(){
        ShotGun sg = new ShotGun(); // ShotGun 클래스를 통해 객체 생성
        sg.setModel("Super ShotGun"); //ShotGun 클래스에 이미 오버라이딩 되어 있으니까
        return sg; // @Bean 메소드는 '반드시 객체를 반납'
    }
}

// Bean 메소드는 반드시 객체를 반납해야 한다
// @Configuration + @Bean