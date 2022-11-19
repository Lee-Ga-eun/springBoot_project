package com.shop.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotaion.GetMapping;
//import org.springframework.web.bind.annotaion.RestController;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// hello world 출력해보기 - p36
@EnableJpaAuditing
@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ShopApplication.class, args);
	}
}
