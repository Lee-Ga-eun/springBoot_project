package com.shop.shop.service;

import com.shop.shop.dto.EachUnivBoardDto;
import com.shop.shop.entity.EachUnivBoardEntity;
import com.shop.shop.repository.EachUnivBoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EachUnivBoardService {
    private EachUnivBoardRepository eachUnivBoardRepository;

    public EachUnivBoardService(EachUnivBoardRepository eachUnivBoardRepository){
        this.eachUnivBoardRepository=eachUnivBoardRepository;
    }

    @Transactional
    public Long savePost(EachUnivBoardDto eachUnivBoardDto){
        System.out.println("넘어오는지 확인"+eachUnivBoardDto);
        return eachUnivBoardRepository.save(eachUnivBoardDto.toEntity()).getId();
        //return  eachUnivBoardRepository.save(eachUnivBoardDto.toEntity()).getId();
    }
    @Transactional
    public EachUnivBoardEntity savePosts(EachUnivBoardEntity eachUnivBoardEntity){
        //System.out.println("넘어오는지 확인"+eachUnivBoardDto);
        return eachUnivBoardRepository.save(eachUnivBoardEntity);
        //return  eachUnivBoardRepository.save(eachUnivBoardDto.toEntity()).getId();
    }

    @Transactional
    public EachUnivBoardDto getPost(Long id){
        EachUnivBoardEntity eachUnivBoardEntity=eachUnivBoardRepository.findById(id).get();

        EachUnivBoardDto eachUnivBoardDto=EachUnivBoardDto.builder()
                .id(eachUnivBoardEntity.getId())
                .author(eachUnivBoardEntity.getAuthor())
                .title(eachUnivBoardEntity.getTitle())
                .content(eachUnivBoardEntity.getContent())
                .fileId(eachUnivBoardEntity.getFileId())
                .createdDate(eachUnivBoardEntity.getCreatedDate())
                .build();
        return eachUnivBoardDto;
    }

    @Transactional
    public List<EachUnivBoardDto> getBoardList() {
        List<EachUnivBoardEntity> boardList = eachUnivBoardRepository.findAll();
        List<EachUnivBoardDto> eachUnivBoardDtoList = new ArrayList<>();

        for(EachUnivBoardEntity eachUnivBoardEntity : boardList) {
            EachUnivBoardDto boardDto = EachUnivBoardDto.builder()
                    .id(eachUnivBoardEntity.getId())
                    .author(eachUnivBoardEntity.getAuthor())
                    .title(eachUnivBoardEntity.getTitle())
                    .content(eachUnivBoardEntity.getContent())
                    .createdDate(eachUnivBoardEntity.getCreatedDate())
                    .fileId(eachUnivBoardEntity.getFileId())
                    .build();
            eachUnivBoardDtoList.add(boardDto);
        }
        return eachUnivBoardDtoList;
    }

    //EachUnivBoardEntity eachUnivBoardEntity= EachUnivBoardRepository.

}
