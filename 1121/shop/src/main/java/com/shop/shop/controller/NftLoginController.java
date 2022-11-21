package com.shop.shop.controller;

import com.shop.shop.dto.NftMemberDto;
import com.shop.shop.entity.NftMember;
import com.shop.shop.repository.NftEmailRepository;
import com.shop.shop.repository.NftMemberRepository;
import com.shop.shop.service.NftMemberService;
import com.shop.shop.service.UnivNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
// 로그인 컨트롤러
@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class NftLoginController {
    private final NftMemberService nftMemberService;
    private final PasswordEncoder passwordEncoder;
    private final UnivNameService univNameService;

    private final NftMemberRepository nftMemberRepository;
    private final NftEmailRepository nftEmailRepository;


    @GetMapping(value = "/lgn")
    public String login(){
        return "nft_member/lgn";
    }

    @PostMapping(value="/lgn")
    public String formLogin(String email, Model model){

        // service에서 findByEmail에 list가 담겨있으면 home에 안녕하세요 누구누구님 출력
        List<String> univName=univNameService.findAll();
        List<String> all=nftMemberService.findByEmail(email);

        if(all.size()!=0){
            List<String> tmp=new ArrayList<>();
            model.addAttribute("loginSuccess", all);
            System.out.println("all"+all);
            tmp.add(all.get(0));
            System.out.println(tmp); // 대학교이름이 "우리학교찾기"의 파라미터와 같을 때만 접속 가능하도록 해보기
            model.addAttribute("univName",univName);
            return "NFT_main";

            //return "redirect:/";
            //return "fragments/NFT_header";
        }else{
            model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
            return "nft_member/lgn";
        }
    }




    @GetMapping(value = "/lgn/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "nft_member/lgn";
    }


//
//    @GetMapping (value = "/lgn")
//    public String lgn(@Valid NftMemberDto nftMemberDto, BindingResult bindingResult, Model model) {
//            model.addAttribute("nftMemberDto", new NftMemberDto());
//            return "nft_member/lgn";
//    }
//
//    @PostMapping(value="/lgn")
//    public String lgnCheck(@Valid NftMemberDto nftMemberDto, BindingResult bindingResult, Model model) {
//
//        if(bindingResult.hasErrors()){
//            return "nft_member/lgn";
//        }
//
//        try{ //그냥.....이메일이 있는지만 확인하고 있으면 redirect 해보는 방법으로..
//            //NftMember nftMember = NftMember.createMember(nftMemberDto, passwordEncoder);
//            nftMemberRepository.findByEmail(nftMemberDto.getEmail());
//            //nftMemberService.saveMember(nftMember);
//        }catch (IllegalStateException e){
//            model.addAttribute("errormessage", e.getMessage());
//            return "nft_member/lgn";
//        }
//
//        //return "redirect:/";
//        return "redirect:/";
//    }


}