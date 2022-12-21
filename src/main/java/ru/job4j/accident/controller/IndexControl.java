package ru.job4j.accident.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControl {
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "John Doe");
        return "index";
    }
}
