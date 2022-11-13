package com.shop.shop.controller;

import com.shop.shop.dto.AllBoardDto;
import com.shop.shop.service.AllBoardService;
import groovy.util.logging.Slf4j;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j

@Controller
public class NFT_CommunityAllController {

    private AllBoardService allBoardService;

    @GetMapping("/communityall")
    public String CommunityAll(){
        return "univ/communityAll";
    }

    @GetMapping("/communityall/new") //전체 커뮤니티 글쓰기 getmapping
    public String write(){
        return "univ/allCommunityNew";
    }

    @PostMapping("/communityall/new") // 전체 커뮤니티 글쓰기 post
    public String write(AllBoardDto allBoardDto){
        allBoardService.savePost(allBoardDto);
        return "redirect:/";
    }

}
