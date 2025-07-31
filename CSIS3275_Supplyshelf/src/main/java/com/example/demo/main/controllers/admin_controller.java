package com.example.demo.main.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Enumeration;

@Controller
public class admin_controller {

    @ControllerAdvice
    public class GlobalControllerAdvice {

        @ModelAttribute("isLoggedIn")
        public boolean isLoggedIn(Principal principal) {
            return principal != null;
        }
    }


    @GetMapping("/admin")
    public String adminPage(Model model, HttpSession session) {

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object value = session.getAttribute(name);
            System.out.println("Attribute Name: " + name + ", Value: " + value);
        }

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null) {
            isLoggedIn = false;
        }
        model.addAttribute("isLoggedIn", isLoggedIn);

        return "admin-page";
    }


}
