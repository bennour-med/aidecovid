package com.tww.aidecovid.controller;

import com.tww.aidecovid.model.User;
import com.tww.aidecovid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String index(Model model) {
        List<User> users = service.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("title", "Liste des utilisateurs");

        return "user/index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        throw new RuntimeException("ceci est un test");
        //return "user/register";
    }

    @PostMapping("user/register")
    public String addUser(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/register";
        }

        String passwordCrypt = user.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(passwordCrypt);

        user.setPassword(encodedPassword);

        service.addUser(user);

        return "redirect:/login";
    }

}
