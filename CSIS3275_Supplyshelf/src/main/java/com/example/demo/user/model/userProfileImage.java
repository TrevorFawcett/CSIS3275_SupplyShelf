package com.example.demo.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class userProfileImage {

    private String image_url;
    private Boolean permission;
    private String document_id;

    public userProfileImage() {}

    public userProfileImage(String image_url, Boolean permission, String document_id) {}

}
