package com.example.tinder2;

import android.content.Context;
import android.content.Intent;
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

import pl.droidsonroids.gif.GifImageView;


public class GuideActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private Context ctx;
    private LinearLayout mDotLayout;

    private SliderAdapter slideradapter;
    private TextView[] mDots;
    TextView Heading;

    private Button nextButton,backButton;
    public int mcurrentpg;
    public GifImageView gifImageView1,gifImageView2,gifImageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mSlideViewPager=findViewById(R.id.slideViewPager);
        mDotLayout=findViewById(R.id.dotsLayout);

        nextButton=findViewById(R.id.nextbutton);
       // backButton=findViewById(R.id.backbutton);

        slideradapter=new SliderAdapter(this);
        gifImageView1=findViewById(R.id.gif1);
        gifImageView2=findViewById(R.id.gif2);
        gifImageView3=findViewById(R.id.gif3);
        Heading=findViewById(R.id.heading);
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
                nextButton.setEnabled(false);
                nextButton.setText("");
                Heading.setText("Choosing your desired region.");

                gifImageView1.setVisibility(View.VISIBLE);
                gifImageView2.setVisibility(View.INVISIBLE);
                gifImageView3.setVisibility(View.INVISIBLE);


            }
            if(i==1)
            {


                 Heading.setText("Swiping cards.");
                nextButton.setEnabled(false);
                nextButton.setText("");
                gifImageView1.setVisibility(View.INVISIBLE);
                gifImageView2.setVisibility(View.VISIBLE);
                gifImageView3.setVisibility(View.INVISIBLE);
            }
            if(i==2)
            {
                nextButton.setEnabled(true);

                Heading.setText("We value your suggestion.");
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(GuideActivity.this,ChooseTask.class);
                        startActivity(intent);
                        finish();
                    }
                });
                nextButton.setText("Finish");

                gifImageView1.setVisibility(View.INVISIBLE);
                gifImageView2.setVisibility(View.INVISIBLE);
                gifImageView3.setVisibility(View.VISIBLE);

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
