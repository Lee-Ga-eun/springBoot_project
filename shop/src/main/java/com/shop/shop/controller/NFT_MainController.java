
package com.shop.shop.controller;

import com.shop.shop.dto.UnivListDto;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Slf4j

@Controller
public class NFT_MainController {
    @GetMapping("/")
    public String index(Model model){
//        return "main"; // 수업 실습 main 화면
        return "NFT_main"; // nft project 연동
    }

    @GetMapping("/about")
    public String about(Model model){
        return "NFT_about";
    }

    @GetMapping("/univlist")
    public String list(Model model){

        List<UnivListDto> univListDtoList=new ArrayList<>();

        // 학교 리스트 배열에 저장하기
        //String[] univ={"가천대학교","숭실대학교","한양대학교"};
        String[] univ={"가천대학교","강릉원주대학교",
                "강원대학교",
                "경북대학교",
                "공주대학교",
                "금오공과대학교",
                "부경대학교",
                "부산대학교",
                "서울대학교",
                "전남대학교",
                "전남대학교",
                "전북대학교",
                "제주대학교",
                "충남대학교",
                "충북대학교",
                "한국체육대학교",
                "한국해양대학교",
                "한국교통대학교",
                "서울과학기술대학교",
                "서울시립대학교",
                "가톨릭대학교",
                "강남대학교",
                "건국대학교",
                "건양대학교",
                "경기대학교",
                "경성대학교",
                "경주대학교",
                "경희대학교",
                "계명대학교",
                "고려대학교",
                "광운대학교",
                "국민대학교",
                "극동대학교",
                "나사렛대학교",
                "단국대학교",
                "대구대학교",
                "대전대학교",
                "대진대학교",
                "덕성여자대학교",
                "동국대학교",
                "동덕여자대학교",
                "동양대학교",
                "명지대학교",
                "배재대학교",
                "삼육대학교",
                "상명대학교",
                "상지대학교",
                "서강대학교",
                "서경대학교",
                "서울여자대학교",
                "성공회대학교",
                "성균관대학교",
                "성신여자대학교",
                "세종대학교",
                "수원대학교",
                "숙명여자대학교",
                "순천향대학교",
                "숭실대학교",
                "아주대학교",
                "안양대학교",
                "안양대학교",
                "연세대학교",
                "영남대학교",
                "용인대학교",
                "을지대학교",
                "을지대학교",
                "이화여자대학교",
                "인하대학교",
                "전주대학교",
                "중앙대학교",
                "중앙대학교",
                "백석대학교",
                "포항공과대학교",
                "한국외국어대학교",
                "한국항공대학교",
                "한성대학교",
                "한양대학교",
                "한양대학교(ERICA)",
                "홍익대학교",
                "금강대학교",
                "우송대학교",
                "남서울대학교",
                "한국공학대학교",
                "울산과학기술원",
                "광주교육대학교",
                "대구교육대학교",
                "서울교육대학교",
                "경인교육대학교",
                "경인교육대학교",
                "전주교육대학교",
                "한국방송통신대학교",
                "한국과학기술원",
                "광주과학기술원",
                "대구경북과학기술원",
                "인천대학교",
                "단국대학교",
                "을지대학교",
                "홍익대학교",
                "상명대학교"};

        for(int i=0;i<=98;i++) {

        UnivListDto univListDto = new UnivListDto();
        //System.out.println("학교이름출력"+univ[i]);
        univListDto.setName(univ[i]);
        univListDtoList.add(univListDto);

        //univListDto.setName("숭실대학교");
        //univListDtoList.add(univListDto);
        }

        //model.addAttribute("univListDto",univListDto);
        model.addAttribute("univListDtoList",univListDtoList);
        //model.addAttribute("univListDto",new UnivListDto());
        return "univ/univlist";
    }
//    @GetMapping("/univlist/가천대학교")
//    public String viewtest(){
//        return "NFT_main";
//    }
        @GetMapping(value="/univlists")
        public String univPages(String univ_param, Model model){
            model.addAttribute("univ_param",univ_param);
        return "univ/univpage";
   }




//    NFT 추가 (월요일)

}