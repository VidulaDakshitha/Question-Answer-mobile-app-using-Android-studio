package com.example.madforumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainFeed extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private ImageView profileIcon;

//    initializing the fragments
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private AskFragment askFragment;
    private SettingsFragment settingsFragment;
    private FeedbackFragment feedbackFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        homeFragment = new HomeFragment();
        askFragment = new AskFragment();
        settingsFragment = new SettingsFragment();
        feedbackFragment = new FeedbackFragment();
        profileFragment = new ProfileFragment();
        profileIcon = findViewById(R.id.profileIcon);
        bottomNavigationView = findViewById(R.id.mainNavBar);
        frameLayout = findViewById(R.id.navFrame);


        changeFragment(homeFragment);

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(profileFragment);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navBtnHome:
                        changeFragment(homeFragment);
                        return true;

                    case R.id.navBtnAsk:
                        changeFragment(askFragment);
                        return true;
                    case R.id.navBtnContact:
                        //changeFragment(feedbackFragment);
                        Intent contactUsIntent = new Intent(MainFeed.this, ContactUs.class);
                        startActivity(contactUsIntent);
                        return true;
                    case R.id.navBtnSettings:
                        //bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                        changeFragment(settingsFragment);
                        return true;

                        default:
                            return false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        changeFragment(homeFragment);
    }

    private void changeFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navFrame, fragment);
        fragmentTransaction.commit();

    }
}
