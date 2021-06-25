package com.example.homework1android2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.homework1android2.FireModel;
import com.example.homework1android2.FireStoreAdapter;
import com.example.homework1android2.databinding.FragmentFireStoreBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class FireStore extends Fragment {

    private FragmentFireStoreBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FireStoreAdapter adapter;
    FireModel fireModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFireStoreBinding.inflate(inflater, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        setupFireStore();
        getDataFromFireStore();
        return binding.getRoot();
    }

    private void getDataFromFireStore() {
        firebaseFirestore.collection("allMessage")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                          //  binding.textFire.append(document.getString("key") + "\n");
                        }
                    } else {
                        Log.w("TAG", "Error getting documents.", task.getException());
                    }
                });

    }

    private void setupFireStore() {
        binding.viewSend.setOnClickListener(v -> {
            Map<String, Object> message = new HashMap<>();
            message.put("key", binding.inputEdit.getText().toString());


            firebaseFirestore.collection("allMessage").add(message).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {
                        Log.e("TAG", "Very good");
                    } else {
                        Log.e("TAG", "Not it is a bad" + task.toString());
                    }
                }
            });
             getDataFromFireStore();
             binding.inputEdit.setText("");
           //  binding.textFire.setText("");
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}