

package com.shop.shop.repository;

import com.shop.shop.entity.AllBoardEntity;
import com.shop.shop.entity.NftCommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NFT_CommunityEachUnivRepository extends JpaRepository<NftCommunityEntity,Long> {
    List<NftCommunityEntity> findByWriter(String writer);
}
