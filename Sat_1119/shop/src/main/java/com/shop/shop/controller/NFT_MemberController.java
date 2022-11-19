package com.shop.shop.controller;

import com.shop.shop.dto.NftMemberDto;
import com.shop.shop.entity.NftMember;
import com.shop.shop.repository.NftMemberRepository;
import com.shop.shop.service.NftMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class NFT_MemberController {
    private final NftMemberService nftMemberService;
    private final PasswordEncoder passwordEncoder;

    private final NftMemberRepository nftMemberRepository;


    @GetMapping(value = "/register")
    public String memberForm(Model model) {
        model.addAttribute("nftMemberDto", new NftMemberDto());
        return "nft_member/register";
    }

//    @PostMapping(value = "/register")
//    public String newMember(NftMemberDto nftMemberDto, BindingResult bindingResult, Model model) {
//
//        NftMember nftMember = NftMember.createMember(nftMemberDto, passwordEncoder);
//        nftMemberService.saveMember(nftMember);
//        //return "redirect:/";
//        return "redirect:/";
//    }

    @PostMapping(value = "/register")
    public String newMember(@Valid NftMemberDto nftMemberDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "nft_member/register";
        }

        try{
            NftMember nftMember = NftMember.createMember(nftMemberDto, passwordEncoder);
            nftMemberService.saveMember(nftMember);
        }catch (IllegalStateException e){
            model.addAttribute("errormessage", e.getMessage());
            return "nft_member/register";
        }

        //return "redirect:/";
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "nft_member/login";
    }


    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "nft_member/login";
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