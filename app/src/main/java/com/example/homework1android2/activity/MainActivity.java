package com.example.homework1android2.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.homework1android2.PreferenceHelper;
import com.example.homework1android2.R;
import com.example.homework1android2.databinding.ActivityMainBinding;
import com.example.homework1android2.onBoard.SecondActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    ImageView imPicture;
    TextView txtMenu;
    private NavController navController;
    final int RECUST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (PreferenceHelper.getShowBoard()){
            startActivity(new Intent(this, SecondActivity.class));

        }
        setSupportActionBar(binding.appBarMain.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull @NotNull NavController controller, @NonNull @NotNull NavDestination destination, @Nullable @org.jetbrains.annotations.Nullable Bundle arguments) {
                if (destination.getId() == R.id.formFragment) {
                    binding.appBarMain.fab.hide();
                    binding.appBarMain.toolbar.setVisibility(View.GONE);
                } else {
                    binding.appBarMain.toolbar.setVisibility(View.VISIBLE);
                    binding.appBarMain.fab.show();
                }
                if (destination.getId() == R.id.authFragment) {
                    binding.appBarMain.toolbar.setVisibility(View.GONE);
                    binding.appBarMain.fab.setVisibility(View.GONE);
                }
                 if (destination.getId() == R.id.nav_gallery){
                     binding.appBarMain.toolbar.setVisibility(View.GONE);
                     binding.appBarMain.fab.setVisibility(View.GONE);
                 }
                  if (destination.getId() == R.id.nav_slideshow){
                      binding.appBarMain.toolbar.setVisibility(View.GONE);
                      binding.appBarMain.fab.setVisibility(View.GONE);
                  }
            }

        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            navController.navigate(R.id.authFragment);
     //        finish();
        }
        addImage();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_home_to_formFragment);
            }
        });
    }

    private void addImage() {
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        // Handle the returned Uri
                        try {
                            final InputStream imageStream = getContentResolver().openInputStream(uri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            imPicture.setImageBitmap(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });
        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        View header = navigation.getHeaderView(0);
        imPicture = (ImageView) header.findViewById(R.id.image_neader);
        imPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}