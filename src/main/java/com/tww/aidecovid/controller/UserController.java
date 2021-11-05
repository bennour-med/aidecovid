package com.tww.aidecovid.controller;


import com.tww.aidecovid.model.Member;
import com.tww.aidecovid.repository.MemberRepository;
import com.tww.aidecovid.repository.RoleRepository;
import com.tww.aidecovid.service.MemberService;
import com.tww.aidecovid.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    MemberService service;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleService userRoleService;

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
/*
    @PostMapping("/process_login")
    public String process_login(Model model){
        String email = (String) model.getAttribute("email");
        Optional<Member> member = repository.findByEmail(email);
        if (member.isPresent()){
            String password = (String) model.getAttribute("password");
            if(member.get().getPassword().equals(password)){
                return "member/show";
            }

        }
        return "member/login";
    }
   */

}
