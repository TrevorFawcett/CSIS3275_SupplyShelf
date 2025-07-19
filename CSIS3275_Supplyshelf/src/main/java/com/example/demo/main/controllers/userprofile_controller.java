package com.example.demo.main.controllers;

import com.example.demo.user.model.userProfileImage;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Enumeration;

@Controller
public class userprofile_controller {


    @GetMapping("userLogin")
    public String profile(Model model, HttpSession session) {

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object value = session.getAttribute(name);
            System.out.println("Attribute Name: " + name + ", Value: " + value);
        }

        userProfileImage pfp = new userProfileImage();
        model.addAttribute("userPfp",pfp);

        return "userprofile";
    }


}
