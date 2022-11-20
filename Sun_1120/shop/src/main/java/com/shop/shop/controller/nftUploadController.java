package com.shop.shop.controller;

import com.shop.shop.dto.AllBoardDto;
import com.shop.shop.dto.EachUnivBoardDto;
import com.shop.shop.dto.NftFileDto;
import com.shop.shop.dto.NftMemberDto;
import com.shop.shop.entity.EachUnivBoardEntity;
import com.shop.shop.entity.Member;
import com.shop.shop.repository.EachUnivBoardRepository;
import com.shop.shop.service.EachUnivBoardService;
import com.shop.shop.service.NftFileService;
import com.shop.shop.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class nftUploadController {

    private EachUnivBoardRepository eachUnivBoardRepository;
    private EachUnivBoardService eachUnivBoardService;
    private NftFileService nftFileService;

    public nftUploadController(EachUnivBoardService eachUnivBoardService, NftFileService nftFileService){
        this.eachUnivBoardService=eachUnivBoardService;
        this.nftFileService=nftFileService;
    }
    @GetMapping("/searchUnivssssssssss/{name}/board") // 해당 대학교의 게시판 페이지
    public String univBoard(Model model, @PathVariable("name") String name, Long id){
        try{
            List<EachUnivBoardDto> all=eachUnivBoardService.getBoardList();
            model.addAttribute("post",all);
        }catch(Exception e){
            model.addAttribute("noPostMessage","등록된 글이 없습니다");
        }
        //List<EachUnivBoardDto> all=eachUnivBoardService.getBoardList();
        //model.addAttribute("post",all);
        model.addAttribute("univName",name);
        return "univ/univBoard";
    }
    //    nft 상품 등록 페이지
    @GetMapping("/searchUnivssssssss/{name}/board/new")
    public String itemForm(Model model, @PathVariable("name") String name){
        model.addAttribute("eachUnivBoardDto", new EachUnivBoardDto());
        model.addAttribute("univName",name);
        return "nft_item/itemForm";
    }

//    public String write(Model model, AllBoardDto allBoardDto){
//        //allBoardService.savePost(allBoardDto);
//        //allBoardService.savePost(allBoardDto);
//        try{
//            allBoardService.savePost(allBoardDto);
//            model.addAttribute("successMessage","게시글 등록 성공?");
//            //return "univ/communityAll";
//        }catch(Exception e){
//            model.addAttribute("errorMessage","게시글 등록 에러");
//            return "nft_item/itemForm";
//        }
//        return "redirect:/communityAll"; // 글쓰기 완료시 redirect
//    }

    @PostMapping("/searchUnivssssssss/{name}/board/new")
        public String write(@PathVariable("name") String name, Model model, EachUnivBoardDto eachUnivBoardDto){
        System.out.println(eachUnivBoardDto);
        try{
              EachUnivBoardEntity eachUnivBoardEntity=EachUnivBoardEntity.createBoard(eachUnivBoardDto);
              eachUnivBoardService.savePosts(eachUnivBoardEntity);
              //eachUnivBoardService.savePost(eachUnivBoardDto);
              model.addAttribute("successMessage","각 대학 별 게시판 게시글 등록 성공");
        }catch(Exception e){
              System.out.println("넘어가지 않음"+eachUnivBoardDto);
            model.addAttribute("errorMessage","게시글 등록 실패");
            return "nft_item/itemForm";
        }
          return "redirect:/searchUniv";
    }

//    @PostMapping("/searchUniv/{name}/board/new")
//    public String write(@RequestParam("file") MultipartFile files, EachUnivBoardDto eachUnivBoardDto) {
//        eachUnivBoardService.savePost(eachUnivBoardDto);
//        try {
//            eachUnivBoardService.savePost(eachUnivBoardDto);
//            String origFilename = files.getOriginalFilename();
//            String filename = new MD5Generator(origFilename).toString();
//            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
//            String savePath = System.getProperty("user.dir") + "\\files";
//            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
//            if (!new File(savePath).exists()) {
//                try{
//                    new File(savePath).mkdir();
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//            }
//            String filePath = savePath + "\\" + filename;
//            files.transferTo(new File(filePath));
//
//            NftFileDto fileDto = new NftFileDto();
//            fileDto.setOrigFilename(origFilename);
//            fileDto.setFilename(filename);
//            fileDto.setFilePath(filePath);
//
//            Long fileId = nftFileService.saveFile(fileDto);
//            eachUnivBoardDto.setFileId(fileId);
//            eachUnivBoardService.savePost(eachUnivBoardDto);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/searchUniv/{name}/board";
//    }
}
