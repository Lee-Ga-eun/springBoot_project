package com.shop.shop.controller;

import com.shop.shop.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // 해당 클래스를 요청하는 컨트롤러로 사용
@RequestMapping("/post")
public class DefaultPostController {

    @PostMapping("/html")
    public String html() {
        return "post/index";
    }

    @PostMapping("/html/{msg}")
    public String html(@PathVariable String msg, Model m) {
        m.addAttribute("msg", msg);
        return "post/index";
    }

    @PostMapping("/txt")
    @ResponseBody // http요청 body를 자바 객체로 전달 받을 수 있다
    public String html(@RequestParam(value="msg", required=false) String msg) {
        return msg;
    }

    @PostMapping("/json")
    @ResponseBody
    public ResponseEntity<Object> html(Model m) {
        m.addAttribute("model","모델값 json");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping("/dto")
    @ResponseBody
    public UserDto html(UserDto userDto) {
        return userDto;
    }

    @PostMapping("/dto-model")
    public String html(UserDto userDto, Model m) {
        m.addAttribute("dto", userDto);
        return "post/index";
    }
}
