package ru.job4j.accident.controller.old;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentServiceSpringData;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;


public class AccidentControllerCrudData {

    private final AccidentServiceSpringData service;

    public AccidentControllerCrudData(AccidentServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        List<Accident> accidents = service.getAll();
        model.addAttribute("user", "John Doe");
        model.addAttribute("accidents", accidents);
        return "accidents";
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
        return "redirect:/accidents";
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
        return "redirect:/accidents";
    }
}