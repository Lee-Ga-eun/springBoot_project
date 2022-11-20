package com.shop.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="nftThymeleaf")
public class NftThymeleafController {

    @GetMapping(value="/home")
    public String nftThymeleafHome(){
        return "thymeleafEx/nftHome";
    }
}
