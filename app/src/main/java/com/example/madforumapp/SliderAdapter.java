package com.example.madforumapp;

import android.content.Context;
import android.graphics.Color;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    SliderAdapter(Context context){
        this.context = context;

        slideHeadings[0] = new SpannableString(context.getString(R.string.titile1));
        slideHeadings[1] = new SpannableString(context.getString(R.string.titile2));
        slideHeadings[2] = new SpannableString(context.getString(R.string.titile3));

        slideDescription[0] =  context.getString(R.string.description1);
        slideDescription[1] =  context.getString(R.string.description2);
        slideDescription[2] =  context.getString(R.string.description3);
    }

    // Images array
    private int[] slide_images = {
            R.drawable.whatisthis,
            R.drawable.people,
            R.drawable.gotaquestion
    };

    private SpannableString[] slideHeadings= new SpannableString[3];
    private String[] slideDescription = new String[3];


    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_content_layout, container, false);

        ImageView imageView = view.findViewById(R.id.imageHolder);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.pageDescription);

        imageView.setImageResource(slide_images[position]);
        title.setText(slideHeadings[position]);
        description.setText(slideDescription[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((androidx.constraintlayout.widget.ConstraintLayout)object);
    }
}
