package com.tww.aidecovid.controller;

import com.tww.aidecovid.model.Service;
import com.tww.aidecovid.model.User;
import com.tww.aidecovid.security.AuthenticationFacade;
import com.tww.aidecovid.service.PresatationService;
import com.tww.aidecovid.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private ServiceService service;
    @Autowired
    private PresatationService presatationService;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/")
    public String index(Model model) {
        String notifCount = "";
        try {
            long count = presatationService.getNotificationCount(authenticationFacade.getAuthenticatedUser());
            if (count > 0) notifCount+=count;
        } catch (Exception ex) {

        }
        model.addAttribute("notifCount", notifCount);
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
    public String demande(Model model) {
        List<Service> services = service.getAllServices();

        model.addAttribute("services", services);
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

    @GetMapping("/discussion")
    public String discussion() {
        return "discussion/index";
    }

}
