package com.shop.shop.controller;

//import com.shop.shop.dto.UnivListDto;
import com.shop.shop.dto.UnivNameDto;
import com.shop.shop.service.UnivNameService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class NftMainController {
    private final UnivNameService univNameService;

    @GetMapping("/") //잠시 m으로 변경하겠음
    public String index(Model model){
//        return "main"; // 수업 실습 main 화면
        List<String> all=univNameService.findAll();
        model.addAttribute("univName",all);

        return "NFT_main"; // nft project 연동
    }

    @GetMapping("/about")
    public String about(Model model){
        return "NFT_about";
    }

    @GetMapping(value="/univlists")
    public String univPages(String univ_param, Model model){
        model.addAttribute("univ_param",univ_param);
        return "univ/univpage";
    }




//    NFT 추가 (월요일)

}