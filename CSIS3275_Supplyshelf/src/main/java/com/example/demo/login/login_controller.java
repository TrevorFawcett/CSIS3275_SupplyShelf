package com.example.demo.login;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class login_controller {

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {


        return "login-page";
    }
}
