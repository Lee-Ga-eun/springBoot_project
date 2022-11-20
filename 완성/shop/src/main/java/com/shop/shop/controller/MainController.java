package com.shop.shop.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j

@Controller
public class MainController {
    @GetMapping("/m")
    public String index(Model model){
        return "main"; // 수업 실습 main 화면
        //return "NFT_main"; // nft project 연동
    }

//    NFT 추가 (월요일)

}