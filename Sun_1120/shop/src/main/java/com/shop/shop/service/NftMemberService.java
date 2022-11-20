package com.shop.shop.service;

import com.shop.shop.dto.NftMemberDto;
import com.shop.shop.dto.UnivNameDto;
import com.shop.shop.entity.NftMember;
import com.shop.shop.entity.UnivName;
import com.shop.shop.repository.NftEmailRepository;
import com.shop.shop.repository.NftMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor //bean 주입방법 생성자 final member, @NonNull member 생성자 생성함
public class NftMemberService implements UserDetailsService {

    private final NftMemberRepository nftMemberRepository;
    private final NftEmailRepository nftEmailRepository;

    public NftMember saveMember(NftMember nftMember){
        validateDuplicateMember(nftMember);
        return nftMemberRepository.save(nftMember);
    }

    // 레포지토리에서 findByEmail해서 찾아온 회원 정보를 담아서 컨트롤로에 보내준다. 컨트롤러에서 이걸 받아서 뷰에 뿌려준다 어서오세요""님""




    private void validateDuplicateMember(NftMember nftMember){
        NftMember findMember = nftMemberRepository.findByEmail(nftMember.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다."); // 예외 처리
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        NftMember nftMember=nftMemberRepository.findByEmail(email);
        System.out.println(nftMember);

        if(nftMember==null){
            throw new UsernameNotFoundException(email);
        }
//        return User.builder()
//                .username(nftMember.getEmail())
//                .password(nftMember.getPassword())
//                .roles(nftMember.getRole().toString())
//                .build();

            return User.builder()
                    .username(nftMember.getEmail())
                    .roles(nftMember.getRole().toString())
                    .build();
    }

    public List<String> findByEmail(String email){
        List<NftMember> nftMembers = new ArrayList<>();
        List<NftMember> all=nftEmailRepository.findByEmail(email);
        List<String> memberList=new ArrayList<>();
        System.out.println(all);
        for(NftMember nftMember:all){
            //NftMemberDto dto=new NftMemberDto(email);
            memberList.add(nftMember.getUniversity());
            memberList.add(nftMember.getName());
            memberList.add(nftMember.getEmail());


        }
        return memberList;
    }//종료
}
