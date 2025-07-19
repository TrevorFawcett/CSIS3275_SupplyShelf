package com.example.demo.main.controllers;

import com.example.demo.user.model.userFormData;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class main_controller {

    @ControllerAdvice
    public class GlobalControllerAdvice {

        @ModelAttribute("isLoggedIn")
        public boolean isLoggedIn(Principal principal) {
            return principal != null;
        }
    }

    @GetMapping("/")
    public String HomePage(Model model, HttpSession session) {

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null) {
            isLoggedIn = false;
        }
        model.addAttribute("isLoggedIn", isLoggedIn);





        return "home";
    }



    @GetMapping("/signup")
    public String signUpPage(Model model) {
        userFormData userNew = new userFormData();
        model.addAttribute("userNew", userNew);


        return "signup-page";
    }



}
