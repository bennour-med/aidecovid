package com.tww.aidecovid.controller;

import com.tww.aidecovid.dto.PrestationDTO;
import com.tww.aidecovid.model.*;
import com.tww.aidecovid.security.AuthenticationFacade;
import com.tww.aidecovid.service.*;
import com.tww.aidecovid.statics.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
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
    private DiscussionService discussionService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/prestations")
    public String getPrestations(Model model, RedirectAttributes redirAttrs, HttpSession session) {
        User user = authenticationFacade.getAuthenticatedUser();
        List<Prestation> providedList = prestationService.getProvidedPrestations(user, Arrays.asList(Status.NEW.getValue(),
                Status.WAITING.getValue(), Status.APPROVED.getValue()));
        List<Prestation> requestedList = prestationService.getRequestedPrestations(user
                , Arrays.asList(Status.WAITING.getValue(), Status.APPROVED.getValue()));
        model.addAttribute("providedList", providedList);
        model.addAttribute("requestedList", requestedList);
        session.setAttribute("notifCount", prestationService.updateNotifCount());
        return "service/prestations";
    }

    @GetMapping("/prestation")
    public String getPrestation(Model model) {
        return "";//findPaginatedUser(1, "lastname", "asc", model);
    }

    @GetMapping("/prestations/service/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Service ser = service.getService(id);
        User user = authenticationFacade.getAuthenticatedUser();
        List<Prestation> prestations = prestationService.getAvailablePrestationsByServiceId(user, ser);
        model.addAttribute("prestations", prestations);
        model.addAttribute("service", ser);
        return "demande/form_demande";
    }

    @PostMapping("prestation/propose")
    public String proposePrestation(@Valid PrestationDTO prestationDTO, Model model, RedirectAttributes redirAttrs, HttpSession session) {
        Prestation prestation = new Prestation();
        prestation.setStatus(Status.NEW.getValue());
        prestation.setCpList(prestationDTO.getCps());
        prestation.setProvider(authenticationFacade.getAuthenticatedUser());
        prestation.setRequester(userService.getUser("1"));
        prestation.setDate(prestationDTO.getDateTime());
        prestation.setService(service.getService(prestationDTO.getServiceId().toString()));
        redirAttrs.addFlashAttribute("success", "Votre offre à été créé avec succes");
        prestationService.save(prestation);
        session.setAttribute("notifCount", prestationService.updateNotifCount());
        return "redirect:/prestations";
    }

    @GetMapping("/prestations/{id}/request")
    public String requestPrestation(Model model, @PathVariable("id") String id, RedirectAttributes redirAttrs, HttpSession session) {
        Prestation prestation = prestationService.getById(Long.parseLong(id)).get();
        prestation.setRequester(authenticationFacade.getAuthenticatedUser());
        prestation.setStatus(Status.WAITING.getValue());
        prestationService.save(prestation);
        redirAttrs.addFlashAttribute("success", "Votre demande à été créé avec succes");
        session.setAttribute("notifCount", prestationService.updateNotifCount());
        return "redirect:/prestations";
    }

    @GetMapping("/prestations/{id}/approve")
    public String approvePrestation(Model model, @PathVariable("id") String id, RedirectAttributes redirAttrs, HttpSession session) {
        Prestation prestation = prestationService.getById(Long.parseLong(id)).get();
        prestation.setStatus(Status.APPROVED.getValue());
        prestationService.save(prestation);
        redirAttrs.addFlashAttribute("success", "Demande acceptée, le demandeur sera notifié");
        session.setAttribute("notifCount", prestationService.updateNotifCount());
        return "redirect:/prestations";
    }

    @GetMapping("/prestations/{id}/decline")
    public String declinePrestation(Model model, @PathVariable("id") String id, RedirectAttributes redirAttrs, HttpSession session) {
        Prestation prestation = prestationService.getById(Long.parseLong(id)).get();
        prestation.setStatus(Status.DECLINED.getValue());
        prestationService.save(prestation);
        redirAttrs.addFlashAttribute("success", "Demande refusée, le demandeur sera notifié");
        session.setAttribute("notifCount", prestationService.updateNotifCount());
        return "redirect:/prestations";
    }

    @GetMapping("/prestations/{id}/close")
    public String closePrestation(Model model, @PathVariable("id") String id, RedirectAttributes redirAttrs, HttpSession session) {
        Prestation prestation = prestationService.getById(Long.parseLong(id)).get();
        prestation.setStatus(Status.DONE.getValue());
        prestationService.save(prestation);
        redirAttrs.addFlashAttribute("success", "Demande cloturée avec succès");
        session.setAttribute("notifCount", prestationService.updateNotifCount());
        return "redirect:/prestations";
    }

    @GetMapping("/prestations/{id}/discussion")
    public String getDiscussions(Model model, @PathVariable("id") String id) {
        Prestation prestation = prestationService.getById(Long.parseLong(id)).get();
        Discussion discussion = prestation.getDiscussion();
        if (discussion == null) {
            discussion = new Discussion();
            discussion.setPrestation(prestation);
            discussion.setCreated(new Date());
            discussionService.save(discussion);
            prestation.setDiscussion(discussion);
            prestationService.save(prestation);
        }
        model.addAttribute("prestation", prestation);
        model.addAttribute("message", new Message());
        return "discussion/messages";
    }

    @PostMapping("/prestations/{id}/discussion/addMessage")
    public String addMessage(@Valid @ModelAttribute("message") Message message, Model model, @PathVariable("id") String id) {
        Prestation prestation = prestationService.getById(Long.parseLong(id)).get();
        Discussion discussion = prestation.getDiscussion();
        message.setCreated(new Date());
        message.setUser(authenticationFacade.getAuthenticatedUser());
        message.setDiscussion(discussion);
        messageService.save(message);
        return "redirect:/prestations/"+id+"/discussion";
    }
}
