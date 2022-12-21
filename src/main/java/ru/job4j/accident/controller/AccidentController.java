package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
@Controller
public class AccidentController {
    private final AccidentService service;

    public AccidentController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("user", "John Doe");
        model.addAttribute("accidents", service.show());
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "createAccident";
    }


    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        service.add(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/formUpdateAccident/{accidentId}")
    public String viewEditAccident(Model model, @PathVariable("accidentId") int id) {
        model.addAttribute("accident", service.findById(id));
        return "editAccident";
    }


    @PostMapping("/updateAccident")
    public String edit(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/accidents";
    }


}
