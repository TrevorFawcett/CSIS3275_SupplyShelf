package com.example.demo.user.service;

import com.example.demo.service.FirebaseConfig;
import com.example.demo.user.model.FBUserData;
import com.example.demo.user.model.userProfileImage;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public List<String> getProfileImages() throws ExecutionException, InterruptedException {


        Firestore dbFireStore = FirestoreClient.getFirestore(app);
        //DocumentReference documentReference = dbFireStore.collection("user_data").document();
        //ApiFuture<DocumentSnapshot> future = documentReference.get();
        //DocumentSnapshot document = future.get();

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFireStore.collection("profile_images").get();
        // future.get() blocks on response
        List<String> userList = new ArrayList<>();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(userProfileImage.class));
            userProfileImage nextImage = document.toObject(userProfileImage.class);
            String username = nextImage.getDocument_id();
            System.out.println(username);
            userList.add(username);
        }





        return userList;
    }

}
