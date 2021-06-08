package com.example.homework1android2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.homework1android2.Adapter.ViewPagerAdapter;
import com.example.homework1android2.R;
import com.example.homework1android2.model.PagerModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    LottieAnimationView lottieAnimationView,animationView, getAnimationView;
    Button bntBegin;
    TextView txtSkip;
    TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        searchView();
        addList();
        initButtons();
        scrollNext();




        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    lottieAnimationView.setVisibility(View.VISIBLE);
                } else {
                    lottieAnimationView.setVisibility(View.GONE);
                }
                  if (position == 1){
                      animationView.setVisibility(View.VISIBLE);
                  } else {
                      animationView.setVisibility(View.GONE);
                  }
                   if (position == 2){
                       getAnimationView.setVisibility(View.VISIBLE);
                   } else{
                       getAnimationView.setVisibility(View.GONE);
                   }
                    if (position == 2){
                        bntBegin.setVisibility(View.VISIBLE);
                    } else {
                        bntBegin.setVisibility(View.GONE);
                    }

                     if (position == 2){
                         txtSkip.setVisibility(View.GONE);
                     } else{
                         txtSkip.setVisibility(View.VISIBLE);
                     }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void scrollNext() {
        txtSkip.setOnClickListener(v -> {
            pager.setCurrentItem(pager.getCurrentItem() + 1);
        });
    }

    private void addList() {
        List<PagerModel> list = new ArrayList<>();
        list.add(new PagerModel("Очень удобный функционал"));
        list.add(new PagerModel("Быстрый качественный продукт"));
        list.add(new PagerModel("Куча функций и интересных фишек"));
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        indicator.setupWithViewPager(pager);
    }

    private void searchView() {
        pager = findViewById(R.id.view_pager);
        lottieAnimationView = findViewById(R.id.animation_view);
        animationView = findViewById(R.id.second_view);
        getAnimationView = findViewById(R.id.third_view);
        txtSkip = findViewById(R.id.all_skip);
        indicator = findViewById(R.id.tab_indiactor);
        bntBegin = findViewById(R.id.button_ready);
    }

    private void initButtons() {

//        txtSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SecondActivity.this, "sdsfsdfs", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
//                startActivity(intent);
//
//            }
//        });
            bntBegin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

    }
}