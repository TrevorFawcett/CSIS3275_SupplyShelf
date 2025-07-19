package com.example.demo.user.service;

import com.example.demo.service.FirebaseConfig;
import com.example.demo.user.model.FBUserData;
import com.example.demo.user.model.userProfileImage;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class profileImageService {

    FirebaseApp app;


    public profileImageService() throws IOException {
        this.app = FirebaseConfig.firebaseApp();
    }

    public String createProfileImage(userProfileImage pfpfImage) throws InterruptedException, ExecutionException
    {

        Firestore dbFireStore = FirestoreClient.getFirestore(app);

        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("profile_images").document(pfpfImage.getDocument_id()).set(pfpfImage);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}
