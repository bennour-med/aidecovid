package com.tww.aidecovid.controller;

import com.tww.aidecovid.model.User;
import com.tww.aidecovid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        return "user/register";
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

    @GetMapping("/users/create")
    public String create(Model model) {
        User user = new User(null,null,null,null,null);

        model.addAttribute("user", user);

        return "user/create";
    }

    @PostMapping("/users/create")
    public String store(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/create";
        }

        service.addUser(user);

        return "redirect:/users/";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        User user = service.getUser(id);

        model.addAttribute("user", user);


        //Générer le lien retour pour l'annulation
        String referrer = request.getHeader("Referer");

        if(referrer!=null && !referrer.equals("")) {
            model.addAttribute("back", referrer);
        } else {
            model.addAttribute("back", "/users/");
        }

        return "user/edit";
    }

    @PutMapping("/users/{id}/edit")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") String id, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        User existing = service.getUser(id);

        if(existing==null) {
            return "user/index";
        }

        Long indice = (long) Integer.parseInt(id);

        user.setId(indice);

        service.updateUser(existing.getId(), user);

        model.addAttribute("user", user);

        return "redirect:/users/";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        User existing = service.getUser(id);

        if(existing!=null) {
            Long indice = (long) Integer.parseInt(id);

            service.deleteUser(indice);
        }

        return "redirect:/users";
    }

}
