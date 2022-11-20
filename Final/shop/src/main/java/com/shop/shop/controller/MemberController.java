package com.shop.shop.controller;

import com.shop.shop.dto.MemberFormDto;
import com.shop.shop.entity.Member;
import com.shop.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }
// 회원정보 검증 및 중복회원가입 조건에 의해 실패한다면, 다시 회원가입 페이지로 돌아가 실패 이유를 출력해줌
    // 검증하려는 객체 앞에 @Valid 어노테이션을 선언하고, 파라미터로 bindingResult 객체를 추가.
    // 검사 후 결과는 bindingResult에 담아준다.
    // bindingResult.hasError()를 통해 에러가 있으면 회원 가입 페이지로 이동시킨다
    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member); // validation 후 repository로 저장해줌
        } catch (IllegalStateException e){ // 중복 가입 에러 처리(service에서 repository로 보내기 전 중복 가입 검증을 함)
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "main"; // 회원가입 성공하면 메인 페이지로 넘어옴 // shop DB에서 member 테이블에서 확인
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }
}
