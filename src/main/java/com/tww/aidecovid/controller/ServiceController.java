package com.tww.aidecovid.controller;

import com.tww.aidecovid.model.Service;
import com.tww.aidecovid.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService service1;

    @GetMapping("/form_offre")
    public String index1(Model model) {
        List<Service> services = service1.getAllServices();

        model.addAttribute("services", services);
        model.addAttribute("title", "Liste des services");

        return "offre/form_offre";
    }

    @GetMapping("/form_demande")
    public String index2(Model model) {
        List<Service> services = service1.getAllServices();

        model.addAttribute("services", services);
        model.addAttribute("title", "Liste des services");

        return "demande/form_demande";
    }

    @GetMapping("/services")
    public String index(Model model) {
        List<Service> services = service1.getAllServices();

        model.addAttribute("services", services);
        model.addAttribute("title", "Liste des services");

        return "service/index";
    }


    @GetMapping("/services/create")
    public String create(Model model) {
        Service service = new Service(null,null);

        model.addAttribute("service", service);

        return "service/create";
    }

   @PostMapping("/services/create")
    public String store(@Valid @ModelAttribute("service") Service service, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "service/create";
        }

        service1.addService(service);

        return "redirect:/services/";
    }

    @GetMapping("/services/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        Service service = service1.getService(id);

        model.addAttribute("service", service);


        //Générer le lien retour pour l'annulation
        String referrer = request.getHeader("Referer");

        if(referrer!=null && !referrer.equals("")) {
            model.addAttribute("back", referrer);
        } else {
            model.addAttribute("back", "/services/");
        }

        return "service/edit";
    }

    @PutMapping("/services/{id}/edit")
    public String update(@Valid @ModelAttribute("service") Service service, BindingResult bindingResult, @PathVariable("id") String id, Model model) {

        if (bindingResult.hasErrors()) {
            return "service/edit";
        }

        Service existing = service1.getService(id);

        if(existing==null) {
            return "service/index";
        }

        Long indice = (long) Integer.parseInt(id);

        service.setId(indice);

        service1.updateService(existing.getId(), service);

        model.addAttribute("service", service);

        return "redirect:/services/";
    }

   @DeleteMapping("/services/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        Service existing = service1.getService(id);

        if(existing!=null) {
            Long indice = (long) Integer.parseInt(id);

            service1.deleteService(indice);
        }

        return "redirect:/services";
    }
}
