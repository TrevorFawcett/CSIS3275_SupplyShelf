package com.example.demo.main.controllers;

import com.example.demo.user.model.FBUserData;
import com.example.demo.user.model.userProfileImage;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Enumeration;
import java.util.List;

import static com.example.demo.Csis3275SupplyshelfApplication.userStore;

@Controller
public class userprofile_controller {

    @ControllerAdvice
    public class GlobalControllerAdvice {

        @ModelAttribute("isLoggedIn")
        public boolean isLoggedIn(Principal principal) {
            return principal != null;
        }
    }

    @GetMapping("userLogin")
    public String profile(Model model, HttpSession session) {

        /*
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object value = session.getAttribute(name);
            System.out.println("Attribute Name: " + name + ", Value: " + value);
        }

         */

        String username = (String) session.getAttribute("username");

        List<FBUserData> list = userStore.getActiveUsers();
        FBUserData activeUser = currentUser(list, username);


        if (activeUser != null) {
            System.out.println("Active User: " + activeUser.getUsername());
            System.out.println("Active User: " + activeUser.isPermissions());
        }



        //System.out.println("Admin user: " + activeUser.isPermissions());

        model.addAttribute("activeUser", activeUser);

        userProfileImage pfp = new userProfileImage();
        model.addAttribute("userPfp",pfp);

        assert activeUser != null;
        if(activeUser.isPermissions()) {
            //return "admin-page";

            return "redirect:/admin";
        }
        else {
            return "userprofile";
        }

    }


    @GetMapping("/uploadpage")
    public String upload(Model model, HttpSession session) {
        //Enumeration<String> attributeNames = session.getAttributeNames();

        return "profileuploadpage";
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
