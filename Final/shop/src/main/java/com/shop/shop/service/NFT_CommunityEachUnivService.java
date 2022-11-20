//package com.shop.shop.service;

//public class NFT_CommunityEachUnivService {
//}

package com.shop.shop.service;


import com.shop.shop.dto.AllBoardDto;
import com.shop.shop.dto.NftCommunityDto;
import com.shop.shop.entity.AllBoardEntity;
import com.shop.shop.entity.NftCommunityEntity;
import com.shop.shop.entity.NftMember;
import com.shop.shop.repository.AllBoardRepository;
import com.shop.shop.repository.NFT_CommunityEachUnivRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NFT_CommunityEachUnivService  {
    private NFT_CommunityEachUnivRepository nft_communityEachUnivRepository;

    @Transactional
    public Long savePost(NftCommunityDto nftCommunityDto) {

        return nft_communityEachUnivRepository.save(nftCommunityDto.toEntity()).getId();
    }

    @Transactional
    public List<NftCommunityDto> getBoardlist(){
        List<NftCommunityEntity> allBoardEntities=nft_communityEachUnivRepository.findAll();
        List<NftCommunityDto> allBoardDtoList=new ArrayList<>();

        for(NftCommunityEntity nftCommunityEntity:allBoardEntities){
            NftCommunityDto nftCommunityDto = NftCommunityDto.builder()
                    .id(nftCommunityEntity.getId())
                    .title(nftCommunityEntity.getTitle())
                    .content(nftCommunityEntity.getContent())
                    .writer(nftCommunityEntity.getWriter())
                    .createdDate(nftCommunityEntity.getCreatedDate())
                    .modifiedDate(nftCommunityEntity.getModifiedDate())
                    .fileId(nftCommunityEntity.getFileId())
                    .univ(nftCommunityEntity.getUniv())
                    .build();
            allBoardDtoList.add(nftCommunityDto);
        }
        return allBoardDtoList;
    }



    public List<String> findByWriter(String writer){
        List<NftCommunityEntity> all=nft_communityEachUnivRepository.findByWriter(writer);
        List<String> writerList=new ArrayList<>();
        for(NftCommunityEntity nftCommunityEntity:all){
            writerList.add(nftCommunityEntity.getWriter());

        }
    return writerList;
    }

    @Transactional
    public List<NftCommunityDto> getUnivName(){
        List<NftCommunityDto> allBoardDtoList=new ArrayList<>();

        for(NftCommunityEntity nftCommunityEntity:nft_communityEachUnivRepository.findAll()){
            NftCommunityDto nftCommunityDto = NftCommunityDto.builder()
                    .id(nftCommunityEntity.getId())
//                    .title(nftCommunityEntity.getTitle())
//                    .content(nftCommunityEntity.getContent())
//                    .writer(nftCommunityEntity.getWriter())
//                    .createdDate(nftCommunityEntity.getCreatedDate())
//                    .modifiedDate(nftCommunityEntity.getModifiedDate())
//                    .fileId(nftCommunityEntity.getFileId())
                    .build();
            allBoardDtoList.add(nftCommunityDto);
        }
        return allBoardDtoList;
    }

    @Transactional
    public NftCommunityDto getPost(Long id) {
        Optional<NftCommunityEntity> boardEntityWrapper = nft_communityEachUnivRepository.findById(id);
        NftCommunityEntity nftCommunityEntity= boardEntityWrapper.get();

        NftCommunityDto nftCommunityDto = NftCommunityDto.builder()
                .id(nftCommunityEntity.getId())
                .title(nftCommunityEntity.getTitle())
                .content(nftCommunityEntity.getContent())
                .writer(nftCommunityEntity.getWriter())
                .createdDate(nftCommunityEntity.getCreatedDate())
                .fileId(nftCommunityEntity.getFileId())
                .build();

        return nftCommunityDto;
    }

    @Transactional
    public void deletePost(Long id) {
        nft_communityEachUnivRepository.deleteById(id);
    }
}
