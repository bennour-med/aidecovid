package com.tww.aidecovid.controller;


import com.tww.aidecovid.model.Member;
import com.tww.aidecovid.repository.MemberRepository;
import com.tww.aidecovid.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    MemberService service;

    @Autowired
    private MemberRepository repository;

    @GetMapping("/members")
    public String getMembers(Model model) {
        List<Member> members = service.getAllMembers();

        model.addAttribute("members", members);
        model.addAttribute("title", "Liste des membres");

        return "member/index";
    }

    @GetMapping("/members/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Member member = service.getById(Long.parseLong(id));

        model.addAttribute("templates/member", member);
        model.addAttribute("title", "Fiche d'un membre");

        return "member/show";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());

        return "member/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(Member member) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String encodedPassword = passwordEncoder.encode(member.getPassword());
        //member.setPassword(encodedPassword);

        repository.save(member);

        return "member/register_success";
    }

    @GetMapping("/login")
    public String login(Model model) {


              return "member/login";
    }

}
