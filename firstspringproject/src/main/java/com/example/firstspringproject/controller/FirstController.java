package com.example.firstspringproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model hiModel){
        hiModel.addAttribute("username","현준");
        return "articles/login";
    }

    @GetMapping("/bye")
    public String seeYouLater(Model byeModel){
        byeModel.addAttribute("byeUserName","현빈");
        return "bye";

    }
}
