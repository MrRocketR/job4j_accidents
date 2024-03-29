package ru.job4j.accident.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class IndexController {

    private final AccidentService service;

    public IndexController(AccidentService service) {
        this.service = service;
    }


    @GetMapping("/index")
    public String accidents(Model model) {
        Collection<Accident> accidents = service.getAll();
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidents);
        return "index";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        Collection<AccidentType> types = service.getTypes();
        Collection<Rule> rules = service.getRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "createAccident";
    }


    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.addAccident(accident, ids);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident/{id}")
    public String viewEditAccident(Model model, @PathVariable("id") int id) {
        Collection<AccidentType> types = service.getTypes();
        Collection<Rule> rules = service.getRules();
        model.addAttribute("accident", service.findById(id));
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "editAccident";
    }


    @PostMapping("/updateAccident")
    public String edit(@ModelAttribute Accident accident, HttpServletRequest req) {
       String[] ids = new String[0];
        service.addAccident(accident, ids);
        return "redirect:/index";
    }
}