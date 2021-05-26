package com.evolenthealth.contacts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/index")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
}