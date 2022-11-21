package com.shop.shop.service;

import com.shop.shop.dto.NftFileDto;
import com.shop.shop.repository.NftFileRepository;
import org.springframework.stereotype.Service;
import com.shop.shop.entity.NftFileEntity;

import javax.transaction.Transactional;
@Service
public class NftFileService {

    private NftFileRepository nftFileRepository;

    public NftFileService(NftFileRepository nftFileRepository){
        this.nftFileRepository=nftFileRepository;
    }

    @Transactional
    public Long saveFile(NftFileDto nftFileDto){
        return nftFileRepository.save(nftFileDto.toEntity()).getId();
    }


    @Transactional
    public NftFileDto getFile(Long id){  //만약 문제가 생긴다면 여기의 Id
        NftFileEntity nftFileEntity=nftFileRepository.findById(id).get();

        NftFileDto nftFileDto=NftFileDto.builder()
                .id(id)
                .origFilename(nftFileEntity.getOrigFilename())
                .filename(nftFileEntity.getFilename())
                .filePath(nftFileEntity.getFilePath())

                .build();
        return nftFileDto;
    }
}
