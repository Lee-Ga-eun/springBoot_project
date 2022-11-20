package com.shop.shop.service;


import com.shop.shop.dto.UnivNameDto;
import com.shop.shop.entity.UnivName;
import com.shop.shop.repository.UnivNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UnivNameService {

    private final UnivNameRepository univNameRepository;

    //public List<UnivName> m() {
      //  List<UnivName> univName = new ArrayList<>();
        //univName = univNameRepository.findAll();

        //return univName;
    //}

    //리스트 테스트.....11/19 12:49
    //public List<UnivNameDto> findByName(String name){
    public List<String> findAll(){
        List<UnivName> all=univNameRepository.findAll();
        List<String> s=new ArrayList<>();
        for(UnivName univName:all){
            s.add(univName.getName());
        }
        System.out.println(s);
        return s;
    }
    public List<String> findByName(String name){
        List<UnivName> all=univNameRepository.findByName(name);
        List<UnivNameDto> univNameDtoList = new ArrayList<>();
        List<String> s=new ArrayList<>();
        for(UnivName univName:all){
//            UnivNameDto dto=UnivNameDto.builder().name(univName.getName()).build();
            //UnivNameDto dto=new UnivNameDto(name);
            UnivNameDto dto=new UnivNameDto(name);
            System.out.println(dto.getName());
            s.add(dto.getName());
            System.out.println("리스트에 담기 전"+s);
            //UnivNameDto dto=UnivNameDto.builder().name(name).build();
            univNameDtoList.add(dto);
            System.out.println(dto);

        }
        return s;
        //return univNameDtoList;
    }//종료
}
