package com.example.demo.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userFormData {

    private String document_id;
    private String username;
    private String email;
    private String dob;
    private String password;


    public userFormData(){
        this.setDocument_id(this.username);
    }
}

