package com.tww.aidecovid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index/index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hi Admin!";
    }
    
    @GetMapping("/member")
    public String member() {
        return "member/index";
    }

}
