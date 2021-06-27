package com.example.homework1android2.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.homework1android2.FireModel;
import com.example.homework1android2.FireStoreAdapter;
import com.example.homework1android2.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FireStoreAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        setAdapter();
        setupFireStore();
        getDataFromFireStore();
        return binding.getRoot();
    }

    private void setAdapter() {
        binding.recViewFire.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FireStoreAdapter();

    }

    private void getDataFromFireStore() {
        firebaseFirestore.collection("allMessage")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<FireModel> list = new ArrayList<>();
                        list.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            FireModel taskmOdel = new FireModel(document.getString("key"));
                            list.add(taskmOdel);
                            //  binding.textFire.append(document.getString("key") + "\n");
                        }
                        adapter.addListOfModel(list);
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
                     if (task.isSuccessful()){
                         Log.e("TAG", "Very good");
                     } else {
                         Log.e("TAG", "Not it is a bad" + task.toString());
                     }
                 }
             });
             getDataFromFireStore();
             binding.inputEdit.setText("");
         });

     }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}