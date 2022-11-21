package com.shop.shop.service;


import com.shop.shop.dto.AllBoardDto;
import com.shop.shop.entity.AllBoardEntity;
import com.shop.shop.repository.AllBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AllBoardService {
    private AllBoardRepository allBoardRepository;

    @Transactional
    public Long savePost(AllBoardDto allBoardDto) {

        return allBoardRepository.save(allBoardDto.toEntity()).getId();
    }

    @Transactional
    public List<AllBoardDto> getBoardlist(){
        List<AllBoardEntity> allBoardEntities=allBoardRepository.findAll();
        List<AllBoardDto> allBoardDtoList=new ArrayList<>();

        for(AllBoardEntity allBoardEntity:allBoardEntities){
            AllBoardDto allBoardDto = AllBoardDto.builder()
                    .id(allBoardEntity.getId())
                    .title(allBoardEntity.getTitle())
                    .content(allBoardEntity.getContent())
                    .writer(allBoardEntity.getWriter())
                    .createdDate(allBoardEntity.getCreatedDate())
                    .modifiedDate(allBoardEntity.getModifiedDate())
                    .build();
            allBoardDtoList.add(allBoardDto);
        }
        return allBoardDtoList;
    }

    @Transactional
    public AllBoardDto getPost(Long id) {
        Optional<AllBoardEntity> boardEntityWrapper = allBoardRepository.findById(id);
        AllBoardEntity allBoardEntity= boardEntityWrapper.get();

        AllBoardDto allBoardDto = AllBoardDto.builder()
                .id(allBoardEntity.getId())
                .title(allBoardEntity.getTitle())
                .content(allBoardEntity.getContent())
                .writer(allBoardEntity.getWriter())
                .createdDate(allBoardEntity.getCreatedDate())
                .build();

        return allBoardDto;
    }

    @Transactional
    public void deletePost(Long id) {
        allBoardRepository.deleteById(id);
    }
}