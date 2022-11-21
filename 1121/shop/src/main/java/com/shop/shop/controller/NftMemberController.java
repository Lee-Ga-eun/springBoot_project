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
public class NftMemberController {
    private final NftMemberService nftMemberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping(value = "/register")
    public String memberForm(Model model) {
        model.addAttribute("nftMemberDto", new NftMemberDto());
        return "nft_member/register";
    }


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

}