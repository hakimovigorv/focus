package com.mpi.focus.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "guys");
        return "home";
    }

}