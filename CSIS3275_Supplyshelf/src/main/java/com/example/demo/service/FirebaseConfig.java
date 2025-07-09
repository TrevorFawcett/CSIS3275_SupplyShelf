package com.example.demo.service;


import com.example.demo.Csis3275SupplyshelfApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Configuration
public class FirebaseConfig {
    private String serviceAccountPath;


    @Bean
    public static FirebaseApp firebaseApp() throws IOException{

        ClassLoader classLoader = Csis3275SupplyshelfApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("ServiceAccountKey.json")).getFile());

        InputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://xxxxx.firebaseio.com")
                .build();


        FirebaseApp app;
        if (FirebaseApp.getApps().isEmpty()) {
            app = FirebaseApp.initializeApp(options, "barcrawl-csis3275");
            System.out.println("initialized Firebase App");
        } else {
            app = FirebaseApp.getApps().get(0);
        }

        return app;
    }



}

/*
@Configuration
public class FirebaseConfig {

    private String serviceAccountPath;

    @Bean
    public static FirebaseApp firebaseApp() throws IOException {

        ClassLoader classLoader = Csis3275Group2024Application.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("ServiceAccountKey.json")).getFile());

        InputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://xxxxx.firebaseio.com")
                .build();


        FirebaseApp app;
        if (FirebaseApp.getApps().isEmpty()) {
            app = FirebaseApp.initializeApp(options, "barcrawl-csis3275");
            System.out.println("initialized Firebase App");
        } else {
            app = FirebaseApp.getApps().get(0);
        }

        return app;
    }


}

*/