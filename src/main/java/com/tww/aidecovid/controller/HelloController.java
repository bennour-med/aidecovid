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

    @GetMapping("/information")
    public String information() {
        return "information/index";
    }

    @GetMapping("/apropos")
    public String apropos() {
        return "apropos/index";
    }

    @GetMapping("/offre")
    public String offre() {
        return "offre/index";
    }

    @GetMapping("/demande")
    public String demande() {
        return "demande/index";
    }

    @GetMapping("/conditions")
    public String condition() {
        return "conditions/index";
    }

    @GetMapping("/confidentialite")
    public String confidentialite() {
        return "confidentialite/index";
    }

}
