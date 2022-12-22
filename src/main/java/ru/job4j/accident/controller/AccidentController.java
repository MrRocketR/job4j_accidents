package ru.job4j.accident.controller;

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
import java.util.List;

@Controller
public class AccidentController {
    private final AccidentService service;

    public AccidentController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        Collection<Accident> accidents = service.showAccidents();
        System.out.println(accidents);
        model.addAttribute("user", "John Doe");
        model.addAttribute("accidents", accidents);
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        Collection<AccidentType> types = service.getTypes().values();
        List<Rule> rules = service.getRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "createAccident";
    }


    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.fillRules(accident, ids);
        service.add(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/formUpdateAccident/{id}")
    public String viewEditAccident(Model model, @PathVariable("id") int id) {
        model.addAttribute("accident", service.findById(id));
        return "editAccident";
    }


    @PostMapping("/updateAccident")
    public String edit(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/accidents";
    }


}
