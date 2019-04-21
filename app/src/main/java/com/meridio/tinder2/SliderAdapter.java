package com.meridio.tinder2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.context=context;
    }

    public int[] slide_image=
            {
                    //list of images
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background
            };

    public String[] slide_headings=
            {
                    "HEADING 1",
                    "HEADING 2",
                    "HEADING 3"

            };
    public String[] slide_descriptions=
            {
                    "Description 1",
                    "Description 2",
                    "Description 3"

            };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==(RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slidelayout,container,false);
      // ImageView slideImageView=(ImageView) view.findViewById(R.id.slide_image);

        TextView slideHaeding=(TextView) view.findViewById(R.id.slide_heading);
        TextView slideDeacription=(TextView) view.findViewById(R.id.slide_des);

       // slideImageView.setImageResource(slide_image[position]);
        //slideHaeding.setText(slide_headings[position]);
        //slideDeacription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}