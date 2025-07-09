package com.example.demo;

import com.example.demo.service.FirebaseConfig;
import com.example.demo.user.service.ActiveUserStore;
import com.example.demo.user.service.userDataService;
import com.google.firebase.FirebaseApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Csis3275SupplyshelfApplication {
	public static FirebaseApp mainApp;
	public static userDataService userService;
	public static ActiveUserStore userStore;

	public static void main(String[] args) throws IOException {
		userService = new userDataService();
		userStore = new ActiveUserStore();

		mainApp = FirebaseConfig.firebaseApp();

		SpringApplication.run(Csis3275SupplyshelfApplication.class, args);
	}

}
