package com.example.demo.main.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main_controller {

    @GetMapping("/")
    public String HomePage(Model model, HttpSession session) {
        /*
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null) {
            isLoggedIn = false;
        }
        model.addAttribute("isLoggedIn", isLoggedIn);

        searchQuery q = new searchQuery();
        model.addAttribute("searchQuery", q);

         */

        return "home";
    }
}
