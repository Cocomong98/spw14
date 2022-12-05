package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET) //이건 루트 주소임 -> 호출하면 그 밑의 index()가 호출된다
    public String home(){

        return "index"; // view를 호출해야 하는데, 제일 좋은 방법은 이름을 부르는 것
    }
}
