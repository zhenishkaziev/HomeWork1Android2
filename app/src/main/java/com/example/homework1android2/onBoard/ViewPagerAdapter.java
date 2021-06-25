package com.example.homework1android2.onBoard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.homework1android2.model.PagerModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List <PagerModel> list;
    FragmentManager manager;

    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fm, List <PagerModel> models) {
        super(fm);
        this.list= models;
        this.manager = fm;

    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
         Fragment fragment = new FragmentPager();
         switch (position){
             case 0:
              fragment = FragmentPager.newInstance(list.get(position).getTitle());
              break;
             case 1:
                 fragment = FragmentPager.newInstance(list.get(position).getTitle());
                 break;
             case 2:
                 fragment = FragmentPager.newInstance(list.get(position).getTitle());
         }
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
