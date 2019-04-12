package com.example.tinder2;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.Button;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GuideActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private Context ctx;
    private LinearLayout mDotLayout;

    private SliderAdapter slideradapter;
    private TextView[] mDots;

    private Button nextButton,backButton;
    public int mcurrentpg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mSlideViewPager=findViewById(R.id.slideViewPager);
        mDotLayout=findViewById(R.id.dotsLayout);

        nextButton=findViewById(R.id.nextbutton);
        backButton=findViewById(R.id.backbutton);

        slideradapter=new SliderAdapter(this);

        mSlideViewPager.setAdapter(slideradapter);
        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void addDotsIndicator(int position)
    {
        mDots=new TextView[3];
        mDotLayout.removeAllViews();
        for(int i=0;i< mDots.length;i++)
        {
            mDots[i]=new TextView(this);

            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            //ctx=getApplicationContext();
            mDots[i].setTextColor(ContextCompat.getColor(this, R.color.transparentWhiteColor));
            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length>0)
        {
            mDots[position].setTextColor(ContextCompat.getColor(this, R.color.White));
        }
    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.SimpleOnPageChangeListener()
    {
        @Override
        public void onPageSelected(int i)
        {
            addDotsIndicator(i);
            mcurrentpg=i;
            if(i==0)
            {
                nextButton.setEnabled(true);
                backButton.setEnabled(false);

                nextButton.setText("Next");
                backButton.setText("");

                backButton.setVisibility(View.INVISIBLE);

            }
            if(i==1)
            {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);

                nextButton.setText("Next");
                backButton.setText("Back");

                backButton.setVisibility(View.VISIBLE);
            }
            if(i==2)
            {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);

                nextButton.setText("Finish");
                backButton.setText("Back");

                backButton.setVisibility(View.VISIBLE);
            }
        }
    };
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        if(html == null){
            // return an empty spannable if the html is null
            return new SpannableString("");
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
}
