package ru.job4j.accident.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexController {

    private final AccidentService service;

    public IndexController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index(Model model) {
        Accident accident1 = new Accident(1, "Drunk", "was Drunk", "DrunkStreet");
        Accident accident2 = new Accident(2, "Test", "was Test", "TestStreet");
        service.add(accident1);
        service.add(accident2);
        model.addAttribute("user", "John Doe");
        model.addAttribute("accidents", service.show());
        return "index";
    }
}
