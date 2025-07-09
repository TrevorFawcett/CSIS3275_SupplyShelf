package com.example.demo.login;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.user.model.FBUserData;
import com.example.demo.user.model.userFormData;
import com.example.demo.user.service.FirebaseUserService;
import com.example.demo.user.service.userDataService;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
//import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Controller;
//import com.csis3275.service.FirebaseUserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class signup_controller {

    public FirebaseUserService userService;
    public userDataService dataService;
    public List<String> usernameList;

    public signup_controller(FirebaseUserService userService, userDataService dataService) throws ExecutionException, InterruptedException {
        this.userService = userService;
        this.dataService = dataService;
        this.usernameList = dataService.getUserNames();
    }

    @PostMapping("/register")
    public String somePostAction(@ModelAttribute("user") userFormData user, RedirectAttributes redirect) throws ExecutionException, InterruptedException, FirebaseAuthException {
        String errorMsg = "";


        if (!checkAge(user.getDob())) {
            errorMsg = errorMsg + "\n -Your age must be at least 21 years";
            //redirAttrs.addFlashAttribute("error", "Your age must be at least 21 years");
            //return "redirect:/signup-page";
        }

        if(user.getPassword().length() < 6){
            errorMsg = errorMsg + "\n -Password must be at least 6 characters";
        }

        if(usernameList.contains(user.getUsername())){
            errorMsg = errorMsg + "\n -Username is already taken";
        }

        if(!errorMsg.isEmpty()){
            redirect.addFlashAttribute("error", errorMsg);
            return "redirect:/signup-page";
        }
        else{
            String email = user.getEmail();
            String password = user.getPassword();
            UserRecord firebaseUser = userService.createUser(email,password);
            FBUserData newUser = new FBUserData();
            newUser.setEmail(email);
            newUser.setUsername(user.getUsername());
            newUser.setDocument_id(firebaseUser.getUid());
            newUser.setDob(user.getDob());

            dataService.createUserData(newUser);
            return "redirect:/";
        }


    }




    public boolean checkAge(String dateOfBirth){

        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(dateOfBirth);

        int age = calculateAge(birthDate, today);
        //System.out.println(age);

        if(age > 21){
            return true;
        }
        else {
            return false;
        }
    }

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {

        return Period.between(birthDate, currentDate).getYears();
    }

    private boolean checkList(String username) throws ExecutionException, InterruptedException {
        List<String> userList = dataService.getUserNames();

        return userList.contains(username);

    }


}