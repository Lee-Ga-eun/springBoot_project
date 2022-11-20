package com.shop.shop.controller;

import com.shop.shop.dto.AllBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/s")
@Controller
@RequiredArgsConstructor
public class EachUnivBoardController {
    //@GetMapping("/searchUniv/{name}/board")
//
//    @GetMapping(value="/communityAll")
//    public String board(Model model){
//        List<AllBoardDto> allBoardDtoList = allBoardService.getBoardlist();
//        model.addAttribute("boardList",allBoardDtoList);
//        return "univ/communityAll";
    //}

//
//    @GetMapping("/searchUniv/{name}/board")
//    public String univBoard(Model model, @PathVariable("name") String name, Long id){
//        //List<EachUnivBoardDto> all=eachUnivBoardService.getBoardList();
//        //model.addAttribute("post",all);
//        model.addAttribute("univName",name);
//        return "univ/univBoard";
//    }

}
