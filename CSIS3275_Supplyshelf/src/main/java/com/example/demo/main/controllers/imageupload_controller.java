package com.example.demo.main.controllers;

import com.example.demo.user.model.userFormData;
import com.example.demo.user.model.userProfileImage;
import com.example.demo.user.service.FirebaseUserService;
import com.example.demo.user.service.profileImageService;
import com.example.demo.user.service.userDataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Destination;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
public class imageupload_controller {

    public profileImageService imgService;


    //private static final String UPLOAD_DIRECTORY = "uploads"; // Or configure in application.properties
    //src\main\resources\static\css\images
    private static final String UPLOAD_DIRECTORY = "src\\main\\resources\\static\\css\\images"; // Or configure in application.properties
    private static String userUsername;

    public imageupload_controller(profileImageService imgService) throws ExecutionException, InterruptedException {
       this.imgService = imgService;
    }


    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("image") MultipartFile file, HttpSession session, @ModelAttribute("userPfp") userProfileImage pfp) {

        userUsername = session.getAttribute("username").toString();

        try {
            Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            //InputStream uploadedFile = file.getInputStream();

            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."));
            //String newFileName = userUsername + "." + extension;
            String newFileName2 = userUsername + extension;

            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Path destinationPath = uploadPath.resolve(newFileName2);

            //Files.copy(file.getInputStream(), filePath);
           Files.copy(file.getInputStream(), destinationPath);
            //Files.copy(filePath, filePath2);
            //Files.move(filePath, destinationPath);

            pfp.setImage_url(newFileName2);
            pfp.setPermission(false);
            pfp.setDocument_id(userUsername);
            imgService.createProfileImage(pfp);

            return "Image uploaded successfully: " + file.getName();
        } catch (IOException | InterruptedException | ExecutionException e) {
            return "Error uploading image: " + e.getMessage();
        }
    }







}
