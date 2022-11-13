package com.shop.shop.controller;
// 우리 학교 찾기에 연결될 페이지입니다

import com.shop.shop.dto.UnivNameDto;
import com.shop.shop.entity.UnivName;
import com.shop.shop.service.UnivNameService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/")
public class NFT_SearchUnivController {
//    여기다가 해보자(11/12)

    private final UnivNameService univNameService;

    //@GetMapping(value="/searchUniv/{name}}")
    @GetMapping("/searchUniv/{name}")
    public String univName(Model model, @PathVariable("name") String name){
        //model.addAttribute("univName",univNameService.getUnivName()); //이건 리스트로 전달한 것임
        //음List<UnivNameDto> all=univNameService.findByName(name);
        //result에 넣어보자
        // 5시 29분.......리스트 UnivNameDto 타입을 String으로 수정해보자
        //List<UnivNameDto> all=univNameService.findByName(name);
        List<String> all=univNameService.findByName(name);
        //new Result<>(all);
        //model.addAttribute("univName",new Result(all));
        model.addAttribute("univName",univNameService.findByName(name));
        return "univ/univName";
    }

    @Data
    @AllArgsConstructor
    public class Result<T> {
        private T data;
    }
//  각 학교별 게시판 (전체 게시판 아님)
    @GetMapping("/searchUniv/{name}/board")
    public String univBoard(Model model, @PathVariable("name") String name){
        model.addAttribute("univName",name);
        return "univ/univBoard";
    }
//    nft 상품 등록 페이지
    @GetMapping("/searchUniv/{name}/board/new")
    public String itemForm(Model model, @PathVariable("name") String name){
        //model.addAttribute("univName",name);
        return "nft_item/itemForm";
    }


    //public String searchUniv(){
        //return "thymeleafEx/nftHome";
      //  return ""; //연결 성공
    //}

}
