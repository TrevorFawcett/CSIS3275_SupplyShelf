package com.example.demo.login;

/*
import com.example.demo.service.FirebaseConfig;
import com.google.firebase.FirebaseApp;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

 */

import com.example.demo.service.FirebaseConfig;
import com.example.demo.user.model.FBUserData;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.example.demo.Csis3275SupplyshelfApplication.userService;
import static com.example.demo.Csis3275SupplyshelfApplication.userStore;
//import java.util.concurrent.TimeUnit;

//import static com.csis3275.Csis3275Group2024Application.userService;
//import static com.csis3275.Csis3275Group2024Application.userStore;

@Controller
public class login_controller {

    @GetMapping("/login-page")
    public String login(Model model, HttpSession session) {


       // return "login-page";
        return "login2";
    }

   @GetMapping("/userLogin")
    public String userLogin(Model model, HttpSession session) {



        return "user-profile";
   }

    FirebaseApp app;


    public login_controller() throws IOException {

        this.app = FirebaseConfig.firebaseApp();
    }

    @RequestMapping(value = "/yourURL", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public String reqControl(@RequestBody Map<String,String> myMap) throws FirebaseAuthException, ExecutionException, InterruptedException {
        List<String> list = new ArrayList<String>();

        for (Map.Entry<String,String> entry : myMap.entrySet()) {
            list.add(entry.getValue());
        }

        //System.out.println(list.get(0));
        String idToken = list.get(0);
        FirebaseToken decodedToken = FirebaseAuth.getInstance(app).verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        System.out.println(uid);

        userService.getUserData(uid);




        return "/login";
    }

    @GetMapping("/login")
    public String userLoggedIn(Model model, HttpSession session) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println(userStore.getActiveUsers());
        List<FBUserData> list = userStore.getActiveUsers();

        if (!list.isEmpty()) {
            FBUserData newUser = currentUser(list, userStore.getUserName());

            model.addAttribute("user", newUser);
            session.setAttribute("isLoggedIn", true);  // Store login status in session
        }
        return "redirect:/";
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

    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "redirect:/";
    }

}
