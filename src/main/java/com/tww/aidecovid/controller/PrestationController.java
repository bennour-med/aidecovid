package com.tww.aidecovid.controller;

import com.tww.aidecovid.dto.PrestationDTO;
import com.tww.aidecovid.model.Prestation;
import com.tww.aidecovid.model.User;
import com.tww.aidecovid.security.AuthenticationFacade;
import com.tww.aidecovid.service.PresatationService;
import com.tww.aidecovid.service.ServiceService;
import com.tww.aidecovid.service.UserService;
import com.tww.aidecovid.statics.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class PrestationController {

    @Autowired
    private PresatationService prestationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ServiceService service;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/prestation")
    public String getPrestation(Model model) {
        return "";//findPaginatedUser(1, "lastname", "asc", model);
    }

    @PostMapping("prestation/propose")
    public String proposePrestation(@Valid PrestationDTO prestationDTO) {
        Prestation prestation = new Prestation();
        prestation.setStatus(Status.NEW.getValue());
        prestation.setCpList(prestationDTO.getCps());
        prestation.setProvider(authenticationFacade.getAuthenticatedUser());
        prestation.setRequester(userService.getUser("1"));
        prestation.setDate(prestationDTO.getDateTime());
        prestation.setService(service.getService(prestationDTO.getServiceId().toString()));
        prestationService.save(prestation);
        return "offre/index";
    }

    @PutMapping("/prestation/{id}/request")
    public String requestPrestation(@PathVariable("id") Long id, String requesterId) {
        Prestation prestation = prestationService.getById(id).get();
        User requester = userService.getUser(requesterId);
        prestation.setRequester(requester);
        prestation.setStatus("inProgress");
        prestationService.save(prestation);
        return "redirect:/users/";
    }
}
