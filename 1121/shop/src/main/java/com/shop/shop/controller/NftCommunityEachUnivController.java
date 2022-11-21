package com.shop.shop.controller;

import com.shop.shop.dto.AllBoardDto;
import com.shop.shop.dto.NftCommunityDto;
import com.shop.shop.dto.NftFileDto;
import com.shop.shop.entity.NftFileEntity;
import com.shop.shop.service.AllBoardService;
import com.shop.shop.service.NFT_CommunityEachUnivService;
import com.shop.shop.service.NftFileService;
import com.shop.shop.util.MD5Generator;
import groovy.util.logging.Slf4j;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NftCommunityEachUnivController {

    private final AllBoardService allBoardService;
    private final NFT_CommunityEachUnivService nft_communityEachUnivService;

    private final NftFileService nftFileService;


    @GetMapping(value="/searchUniv/{name}/board")
    public String board(Model model, @PathVariable("name") String name){
        List<NftCommunityDto> allBoardDtoList = nft_communityEachUnivService.getBoardlist();
        //List<NftCommunityDto> findName=nft_communityEachUnivService.getUnivName();
        //System.out.println(findName);
        //List<String> s=nft_communityEachUnivService.findByWriter();
        //System.out.println(s);
        List<String> university = new ArrayList<>();
        university.add(name);
        //System.out.println("학교이름");
        //String university = name;
        //university=name;
        System.out.println(university);
        model.addAttribute("boardList",allBoardDtoList);
        model.addAttribute("univName",name);
        model.addAttribute("university",university);

        return "nftCommunity/nftCommunityBoard";
    }

    @GetMapping("/searchUniv/{name}/board/new") //전체 커뮤니티 글쓰기 getmapping
    public String write(@PathVariable("name") String name, Model model) {
        model.addAttribute("univName",name);
        return "nftCommunity/nftCommunityNew";
    }
    // ************************************v파일 없이
    @PostMapping("/searchUnivsss/{name}/board/new") // 전체 커뮤니티 글쓰기 post
    public String writes(@PathVariable("name") String name, Model model, @RequestParam("file") MultipartFile files,NftCommunityDto nftCommunityDto){
        //allBoardService.savePost(allBoardDto);
        //allBoardService.savePost(allBoardDto);
        System.out.println(nftCommunityDto);
        try{
            nft_communityEachUnivService.savePost(nftCommunityDto);
            model.addAttribute("successMessage","게시글 등록 성공?");
            //return "univ/communityAll";

            //file 업데이트

        }catch(Exception e){
            model.addAttribute("errorMessage","게시글 등록 에러");
            //return "nft_item/itemForm";
            return "nftCommunity/nftCommunityNew";
        }
        return "redirect:/searchUniv/{name}/board"; // 글쓰기 완료시 redirect
    }

    // 테스트테스트테스트
    @PostMapping("/searchUniv/{name}/board/new") // 전체 커뮤니티 글쓰기 post
    public String write(@PathVariable("name") String name, Model model, @RequestParam("file") MultipartFile files,NftCommunityDto nftCommunityDto){
        //allBoardService.savePost(allBoardDto);
        //allBoardService.savePost(allBoardDto);
        System.out.println(nftCommunityDto);
        try{
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            NftFileDto fileDto = new NftFileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);

            Long fileId = nftFileService.saveFile(fileDto);
            nftCommunityDto.setFileId(fileId);
            nft_communityEachUnivService.savePost(nftCommunityDto);
        } catch(Exception e) {
            e.printStackTrace();
            return "nftCommunity/nftCommunityNew";
        }

        return "redirect:/searchUniv/{name}/board"; // 글쓰기 완료시 redirect
    }

    @GetMapping("/searchUniv/{name}/board/{no}")
    public String boardDetail(@PathVariable("no") Long no,@PathVariable("name") String name, Model model) {
        NftCommunityDto nftCommunityDto = nft_communityEachUnivService.getPost(no); // 게시글 가져오기
        System.out.println(nftCommunityDto);
        System.out.println(nftCommunityDto.getFileId());
        if(nftCommunityDto.getFileId()!=null){
            NftFileDto nftFileDto=nftFileService.getFile(nftCommunityDto.getFileId());
            model.addAttribute("fileDto",nftFileDto);
        }
        //NftFileDto nftFileDto=nftFileService.getFile(nftCommunityDto.getFileId());
        //System.out.println(nftFileDto);
        //model.addAttribute("fileDto",nftFileDto);
        model.addAttribute("boardDto", nftCommunityDto);
        model.addAttribute("univName",name);
        model.addAttribute("no",no);
        return "nftCommunity/nftCommunityDetail";
    }

    @GetMapping("/searchUniv/{name}/board/edit/{no}")
    public String edit(@PathVariable("no") Long no,@PathVariable("name") String name, Model model) {
        NftCommunityDto nftCommunityDto = nft_communityEachUnivService.getPost(no);

        model.addAttribute("boardDto", nftCommunityDto);
        model.addAttribute("no",no);
        model.addAttribute("name",name);

        //return "nftCommunity/nftCommunityNew";
        return "nftCommunity/nftCommunityUpdate";
    }

//    @PostMapping("/searchUniv/{name}/boardboardboard/edit/{no}") // 게시글 수정::: 일단 board를 boardboardboard로 수정함
//    public String allUpdate(@PathVariable("no") Long no, @PathVariable("name") String name, NftCommunityDto nftCommunityDto, Model model) {
//        try{
//            nft_communityEachUnivService.savePost(nftCommunityDto);
//            model.addAttribute("successMessage","게시글 등록 성공?");
//            //return "univ/communityAll";
//        }catch(Exception e){
//            System.out.println("에러 발생");
//            model.addAttribute("errorMessage","게시글 등록 에러");
//            return "nftCommunity/nftCommunityNew";
//            //return "nftCommunity/nftCommunityUpdate";
//        }
//        return "redirect:/searchUniv/{name}/board"; // 글쓰기 완료시 redirect
//        //return "redirect:/searchUniv";
//    }

    //파일 저장 구현을 위해서 다시 진행
    @PostMapping("/searchUniv/{name}/board/edit/{no}") // 전체 커뮤니티 글쓰기 post
    public String updateAdd(@PathVariable("no") Long no, @PathVariable("name") String name, Model model, @RequestParam("file") MultipartFile files,NftCommunityDto nftCommunityDto){
        //allBoardService.savePost(allBoardDto);
        //allBoardService.savePost(allBoardDto);
        //System.out.println(nftCommunityDto);
        try{
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            NftFileDto fileDto = new NftFileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);

            Long fileId = nftFileService.saveFile(fileDto);
            nftCommunityDto.setFileId(fileId);
            nft_communityEachUnivService.savePost(nftCommunityDto);
        } catch(Exception e) {
            e.printStackTrace();
            return "nftCommunity/nftCommunityNew";
        }

        return "redirect:/searchUniv/{name}/board"; // 글쓰기 완료시 redirect
    }


    @PostMapping ("/searchUniv/{name}/board/{no}") //게시글 삭제
    public String delete(@PathVariable("no") Long no) {
        nft_communityEachUnivService.deletePost(no);

        return "redirect:/searchUniv/{name}/board";
    }
    //파일 다운로드
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        NftFileDto fileDto = nftFileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }

}


