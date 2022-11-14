package com.shop.shop.controller;

import com.shop.shop.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
//ResponseBody는 html 작성 없이 Body에 데이터를 담아 http에 전송하는 것을 말한다.
// 따라서, 여기 실습에서는 굳이 html에 전송하는 코드를 작성하지 않는다

//RestController의 주용도는 Json형태로 객체 데이터를 반환한다는 것이다.
//Controller+ResponseBody이다
//최근에 데이터를 응답으로 제공하는 REST API를 개발할 때 주로 사용하며, ResponseEntity로 감싸서 반환한다


@RestController // @Controller + @ResponseBody
@Controller
@RequestMapping("/rest")
public class DefaultRestController {

    //http://localhost:8080/rest/json
    @GetMapping("/json")
    public ResponseEntity<Object> html(Model m) {
        m.addAttribute("model","모델값 json");
        //return new ResponseEntity<>(m, HttpStatus.OK);
        return ResponseEntity.ok(m);
    }
    // @RestController가 아니면 불가능함
    //http://localhost:8080/rest/dto?name=홍길동&phone=01011111111
    @GetMapping("/dto")
    public UserDto html(UserDto userDto) {
        LOGGER.info("dto > json"); // 콘솔 창에 찍힘
        LOGGER.info("userDto에 무엇이 있는가"+userDto);
        return userDto;
    }
    //@RestController가 아니면 불가능함
    //http://localhost:8080/rest/map
    @GetMapping("/map")
    public HashMap<String,String> html() {
        HashMap<String,String> map = new HashMap<>(){{
            put("이름","홍길동");
            put("나이","30");
            put("국적","서울");
        }};
        return map;
    }

    //http://localhost:8080/rest/parm?name=홍길동&phone=01011111111
    // ?와 & 기호를 기준으로 파라미터를 구분
    @GetMapping(value = "/parm")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder(); // Stirng은 변경 불가능한 문자열을 생성하지만 StringBuilder는 변경 가능한 문자열을 만들어 주기 때문

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
//name : 홍길동 phone : 01011111111
        /*
        for (String key : param.keySet()) {
            String value = param.get(key);
            sb.append(key + " : " + value + "\n");
            System.out.println("[key]:" + key + ", [value]:" + value);
        }
        */

        return sb.toString();
    }
}