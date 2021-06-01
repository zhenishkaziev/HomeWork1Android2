package com.example.homework1android2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homework1android2.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;


public class FormFragment extends Fragment {

  private EditText txtMain, txtDescription;
  private TextView txtReady;
    TaskmOdel model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        initView(view);
        initClick();
        return view;
    }

    private void initClick() {
        txtReady.setOnClickListener(v -> {
            String title = txtMain.getText().toString();
            String description = txtDescription.getText().toString();
            model = new TaskmOdel(title, description);
            Bundle bundle = new Bundle();
            bundle.putSerializable("keyModel", model);
            getParentFragmentManager().setFragmentResult("key", bundle);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });
    }
    private void initView(View view) {
        txtMain = view.findViewById(R.id.main_text);
        txtDescription = view.findViewById(R.id.des_text);
        txtReady = view.findViewById(R.id.ready_text);
    }
}