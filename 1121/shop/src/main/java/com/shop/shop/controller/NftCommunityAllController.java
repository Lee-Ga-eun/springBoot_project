package com.shop.shop.controller;

import com.shop.shop.dto.AllBoardDto;
import com.shop.shop.service.AllBoardService;
import groovy.util.logging.Slf4j;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NftCommunityAllController {

    private final AllBoardService allBoardService;

    @GetMapping(value="/communityAll/s")
    public String CommunityAll(){
        return "univ/communityAll";
    }

    @GetMapping(value="/communityAll")
    public String board(Model model){
        List<AllBoardDto> allBoardDtoList = allBoardService.getBoardlist();
        model.addAttribute("boardList",allBoardDtoList);
        return "univ/communityAll";
    }

    @GetMapping("/communityAll/new") //전체 커뮤니티 글쓰기 getmapping
    public String write(){
        return "univ/allCommunityNew";
    }

    @PostMapping("/communityAll/new") // 전체 커뮤니티 글쓰기 post
    public String write(Model model, AllBoardDto allBoardDto){
        //allBoardService.savePost(allBoardDto);
        //allBoardService.savePost(allBoardDto);
        System.out.println(allBoardDto);
        try{
            allBoardService.savePost(allBoardDto);
            model.addAttribute("successMessage","게시글 등록 성공?");
            //return "univ/communityAll";
        }catch(Exception e){
            model.addAttribute("errorMessage","게시글 등록 에러");
            //return "nft_item/itemForm";
            return "univ/allCommunityNew";
        }
        return "redirect:/communityAll"; // 글쓰기 완료시 redirect
    }
    @GetMapping("/communityAll/{no}")
    public String boardDetail(@PathVariable("no") Long no, Model model) {
        AllBoardDto allBoardDto = allBoardService.getPost(no); // 게시글 가져오기

        model.addAttribute("boardDto", allBoardDto);
        return "univ/communityDetail";
    }

    @GetMapping("/communityAll/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        AllBoardDto allBoardDto = allBoardService.getPost(no);

        model.addAttribute("boardDto", allBoardDto);

        return "univ/allCommunityUpdate";
    }

    @PostMapping("/communityAll/edit/{no}") // 게시글 수정:::
    public String allUpdate(@PathVariable("no") Long no, AllBoardDto allBoardDto, Model model) {
        try{
            allBoardService.savePost(allBoardDto);
            model.addAttribute("successMessage","게시글 등록 성공?");
            //return "univ/communityAll";
        }catch(Exception e){
            model.addAttribute("errorMessage","게시글 등록 에러");
            return "nft_item/itemForm";
        }
        return "redirect:/communityAll"; // 글쓰기 완료시 redirect
    }

    @PostMapping ("/communityAll/{no}") //게시글 삭제
    public String delete(@PathVariable("no") Long no) {
        allBoardService.deletePost(no);

        return "redirect:/communityAll";
    }

}
