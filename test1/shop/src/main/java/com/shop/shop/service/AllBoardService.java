package com.shop.shop.service;


import com.shop.shop.dto.AllBoardDto;
import com.shop.shop.repository.AllBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class AllBoardService {
    private AllBoardRepository allBoardRepository;

    @Transactional
    public Long savePost(AllBoardDto boardDto) {
        return allBoardRepository.save(boardDto.toEntity()).getId();
    }
}