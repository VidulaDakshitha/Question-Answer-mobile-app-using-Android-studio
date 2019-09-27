package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    final int numOfPages = 3;

    private ViewPager mMainPage;
    private LinearLayout mDotContainer;
    private  SliderAdapter sliderAdapter;
    private TextView[] mDots;

    private Button mNext;
    private Button mBack;
    private  Button mSignIn;
    private int mCurrentSliderPage=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent mainFeed=new Intent(MainActivity.this,MainFeed.class);
            startActivity(mainFeed);
        }

        mMainPage = findViewById(R.id.mainViewPager);
        mDotContainer = findViewById(R.id.sliderDots);
        mNext = findViewById(R.id.next);
        mBack = findViewById(R.id.back);
        mSignIn = findViewById(R.id.signin);

        sliderAdapter = new SliderAdapter(this);
        mMainPage.setAdapter(sliderAdapter);
        // adding the slider dots
        addDots(0);
        // setting the event listener for the page slider
        mMainPage.addOnPageChangeListener(viewListener);

        DBManipulator.InitializeQuestions();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // setting the event listeners for the next and back buttons
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mCurrentSliderPage+1 < numOfPages)
                    mMainPage.setCurrentItem(mCurrentSliderPage+1);

            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mCurrentSliderPage-1 >= 0)
                    mMainPage.setCurrentItem(mCurrentSliderPage-1);

            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(MainActivity.this,act2.class);
                startActivity(loginIntent);
            }
        });

    }

    // adding the dots
    public void addDots(int position){
        mDots = new TextView[3];
        mDotContainer.removeAllViews();

        for (int i=0; i<numOfPages; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;") );
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(Color.WHITE);

            mDotContainer.addView(mDots[i]);
        }

        if(position>=0){
            mDots[position].setTextColor(Color.LTGRAY);
        }
    }

    // adding a listener for slider
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            mCurrentSliderPage=position;

            if(mCurrentSliderPage == 0){
                mNext.setVisibility(View.VISIBLE);
                mNext.setEnabled(true);

                mBack.setVisibility(View.INVISIBLE);
                mBack.setEnabled(false);
            }
            else{
                mBack.setVisibility(View.VISIBLE);
                mBack.setEnabled(true);
            }

            if(mCurrentSliderPage==numOfPages-1){
                mNext.setVisibility(View.INVISIBLE);
                mNext.setEnabled(false);

                mBack.setVisibility(View.VISIBLE);
                mBack.setEnabled(true);

                mSignIn.setVisibility(View.VISIBLE);
                mSignIn.setEnabled(true);
            }
            else{
                mNext.setVisibility(View.VISIBLE);
                mNext.setEnabled(true);

                mSignIn.setVisibility(View.INVISIBLE);
                mSignIn.setEnabled(false);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
