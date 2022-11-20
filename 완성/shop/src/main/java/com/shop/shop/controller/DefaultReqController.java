
package com.shop.shop.controller;


import com.shop.shop.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;
// post로 받을 것인지, get으로 받을 것인지

@Controller //클래스를 컨트롤러로
@RequestMapping("/req") // 특정 uri로 클라이언트가 요청을 하면, Controller에서 어떤 방식으로 처리할지 정의한다
// 들어온 요청을 특정 메소드와 매핑하기 위해 사용: @RequestMapping
// http://localhost:80/req/html
public class DefaultReqController {
    @RequestMapping(value="/html", method= RequestMethod.GET)
    public String html(){
        return "get/index"; //get의 index파일(html 파일)을 띄어준다
    } //html은 의미 없어보임

    //http://localhost:80/req/html/안녕하세요
    @RequestMapping(value = "/html/{msg}", method = RequestMethod.GET)
    public String html(@PathVariable String msg, Model m) {
//        안녕하세요를 입력하면 msg에 담아 가져오도록 한다: path variable (주소지 안에 슬러시에 있는 값을 가져오는 것)
//        Model m: 스프링 컨테이너가 넘겨줄 것. m에 값을 세팅한다/ html을 보낼 때 모델로 보낸다!
            m.addAttribute("msg", msg); // .addAttribute를 통해 value를 attributeName(msg)에 담아 전달함
            return "get/index"; //index.html에 전달할 때 , model 객체를 전달해줘야 한다. (Controller -> Service이니까)
    }
    //@RequestParam은 @ResponseBody가 없으면 동작하지 않음
    //그러나, @ResponseBody가 있으면 굳이 @RequestParam을 쓰지 않아도 똑같이 동작함
    //http://localhost:80/req/txt?msg=안녕하세요
    @RequestMapping(value = "/txt", method = RequestMethod.GET) //GET 방식으로 데이터를 전달할 것이며, 그 데이터는 msg이며,
    @ResponseBody // 흔히 사용하는 방법은, json 형태로 데이터를 전달.
    public String html(@RequestParam(value="msg", required=false) String msg) { //RequestParam에 들어온 값 전달
            return msg; //순수하게 메시지를 출력해줘
        // html(@RequestParam("msg") String msg)  --> 실행됨
        // http://localhost/req/txt?msgg=hi 로 하면, value랑 맞지 않아서 아무것도 출력되지 않음.
        // @RequestParams("가져올 데이터의 이름")[데이터타입][데이터를 담을 변수]
    }

    @RequestMapping(value = "/txtt", method = RequestMethod.GET) //GET 방식으로 데이터를 전달할 것이며, 그 데이터는 msg이며,
    @ResponseBody // 흔히 사용하는 방법은, json 형태로 데이터를 전달.
    public String htmll(String msg) { //RequestParam에 들어온 값 전달
        return msg; //순수하게 메시지를 출력해줘
        // html(@RequestParam("msg") String msg)  --> 실행됨
        // http://localhost/req/txt?msgg=hi 로 하면, value랑 맞지 않아서 아무것도 출력되지 않음.
        // @RequestParams("가져올 데이터의 이름")[데이터타입][데이터를 담을 변수]
    }

    //http://localhost:80/req/json
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> html(Model m) {
        m.addAttribute("model","모델값 json"); //Json 형태로 반환해줌
        return new ResponseEntity<>(m, HttpStatus.OK); //모델 값을 ResponseEntity에 넣는다. 출력 형태: {"model":"모델값 json"}, Map형태 (혹은 hashmap)
        // view 없이도 html 생성 가능
        //Generic 타입의 List 형태
        // hashmap 세팅 방법: Put
    }
// post이기 때문에, localhost:80/post.html 주소로 접근해야 한다(실질적으로 폼에서 /req/dto에 전달)
    //http://localhost:80/req/dto?name=홍길동&phone=01011111111  get방식으로 따지면 이런 식이다
    @RequestMapping(value = "/dto",  method = RequestMethod.POST)
    @ResponseBody
    public UserDto html(UserDto userDto) // form에서 데이터 보낼 땐, DTO로 보내야 한다
    { //위에 제너릭 함수 볼 것, 객체는 setName, setPhone으로 값이 세팅될 것
        return userDto;
    }
// 데이터 전달해주는 장소" req/dto-model
    @RequestMapping(value = "/dto-model", method = RequestMethod.POST)
    // post 방식으로 받기로 함. post 로 전달한 것에 접속하는 방법  form을 통해 들어간다
    public String html(UserDto userDto, Model m) { // 저장된 userDto를 모델에 담고 전송
        //userDto.getName() 으로 뽑아내야함 , userDto.name 안 됨 (private)
        m.addAttribute("dto", userDto); //모델에 전달:모델 값을 받는 뷰가 있다
        return "post/index"; //모델 값을 받는 뷰 (resources/post/index.html)
    }
}

//method가 Put형태로 한다면, db에 업데이트를 쳐야 함
//delete로 데이터가 들어온다면 데이터를 지워야 함


