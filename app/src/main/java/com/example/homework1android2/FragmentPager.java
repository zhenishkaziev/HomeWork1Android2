package com.example.homework1android2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentPager extends Fragment {

    private static final String ARG_PARAM1 = "param1";


    private String title;
    private TextView txtTitle;

    public FragmentPager() {

    }

    public static FragmentPager newInstance(String name) {
        FragmentPager fragment = new FragmentPager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        txtTitle = view.findViewById(R.id.pager_text);
        setData();
        return view;
    }

    private void setData() {
        txtTitle.setText(title);
    }
}