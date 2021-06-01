package com.example.homework1android2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.homework1android2.R;
import com.example.homework1android2.TaskAdaptrer;
import com.example.homework1android2.TaskmOdel;
import com.example.homework1android2.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    TaskAdaptrer adaptrer;
    TaskmOdel taskmOdel;
    private List list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initRec();
        getDataForm();
        return root;
    }

    private void getDataForm() {
        binding.recycleView.setLayoutManager(new GridLayoutManager(requireContext(),2));
        //binding.recycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), ((requestKey, result) -> {
            TaskmOdel taskmOdel = (TaskmOdel) result.getSerializable("keyModel");
            if (taskmOdel != null) {
                adaptrer.addModel(taskmOdel);
            }
        }));
    }

    private void initRec() {
        list = new ArrayList();
        list.add(new TaskmOdel("Нужно сделать:","Сделать домашку и убраться дома"));
        list.add(new TaskmOdel("Нужно сделать:","Сделать домашку и убраться дома"));
        list.add(new TaskmOdel("Нужно сделать:","Сделать домашку и убраться дома"));
        list.add(new TaskmOdel("Нужно сделать:","Сделать домашку и убраться дома"));
        adaptrer = new TaskAdaptrer(list);
        binding.recycleView.setAdapter(adaptrer);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}