package com.example.demo.main.controllers;

import com.example.demo.user.model.FBUserData;
import com.example.demo.user.model.userFormData;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.demo.Csis3275SupplyshelfApplication.userService;
import static com.example.demo.Csis3275SupplyshelfApplication.userStore;

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
    public String HomePage(Model model, HttpSession session) throws ExecutionException, InterruptedException {

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null) {
            isLoggedIn = false;
        }
        model.addAttribute("isLoggedIn", isLoggedIn);


        /*
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object value = session.getAttribute(name);
            System.out.println("Attribute Name: " + name + ", Value: " + value);
        }
         */

        //System.out.println(checkActiveUser());

        if(isLoggedIn) {
            if(!checkActiveUser()){
                String username = session.getAttribute("username").toString();
                Thread.sleep(200);

                //userService.getUserData();
               FBUserData user = userService.getUser(username);
               System.out.println("User: " + user.getUsername() + " is logged in");
               userStore.addActiveUser(user);
            }
        }



        return "home";
    }



    @GetMapping("/signup")
    public String signUpPage(Model model) {
        userFormData userNew = new userFormData();
        model.addAttribute("userNew", userNew);


        return "signup-page";
    }

    public boolean checkActiveUser(){
        if(!userStore.getActiveUsers().isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    public FBUserData currentUser(List<FBUserData> list, String username){
        FBUserData activeUser;

        for (FBUserData user : list) {
            if (user.getUsername().equals(username)) {
                activeUser = user;
                return activeUser;
            }
        }

        return null;
    }

}
