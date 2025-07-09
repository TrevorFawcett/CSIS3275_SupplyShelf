package com.example.demo.user.service;

//import com.csis3275.model.FBUserData;

import com.example.demo.user.model.FBUserData;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ActiveUserStore {

    private List<FBUserData> activeUsers;



    private String UserName;

    public ActiveUserStore() {
        activeUsers = new ArrayList<>();
    }

    public List<FBUserData> getActiveUsers() {
        return activeUsers;
    }

    public void addActiveUser(FBUserData user) {
        activeUsers.add(user);
    }
    public void deleteUsers() {
        activeUsers.removeAll(activeUsers);
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}

