package com.shop.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class nftUploadController {
    @GetMapping(value="/upload")
    public String uploadNft(Model model){
        return "nft_item/nftUpload";
    }
}
