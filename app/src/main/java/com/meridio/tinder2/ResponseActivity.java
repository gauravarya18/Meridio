package com.meridio.tinder2;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

public class ResponseActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private Context ctx;
    private LinearLayout mDotLayout;
    int value;

    private SliderAdapter slideradapter;
    private TextView[] mDots;
    TextView Heading;
    TextView title,share_un;
    ImageView imageurl;
    RadioGroup reasons;
    String reason_response;

    ArrayList<String> Titles,Urls;

    private Button nextButton,backButton;
    public int mcurrentpg;
    int common[]=new int[7];
    int count=1;int j=0;
    int array[]=new int[7];int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 5000);

        //Catching value
        int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;
        Titles = (ArrayList<String>) getIntent().getSerializableExtra("Titles");
        Urls = (ArrayList<String>) getIntent().getSerializableExtra("Urls");
        final int common_res[] = (int[]) getIntent().getSerializableExtra("common");
        common=common_res;
        array[0]=100;
        for(int k=0;k<=5;k++)
        {
            if(common[k]==0||common[k]==1) {
                array[count++]=k;

                Log.d("common", "i" + k);
            }

        }

        mSlideViewPager=findViewById(R.id.slideViewPager);
        mDotLayout=findViewById(R.id.dotsLayout);

        nextButton=findViewById(R.id.nextbutton);



        mSlideViewPager=findViewById(R.id.slideViewPager);
        mDotLayout=findViewById(R.id.dotsLayout);

        nextButton=findViewById(R.id.nextbutton);
        // backButton=findViewById(R.id.backbutton);

        slideradapter=new SliderAdapter(this);



        mSlideViewPager.addOnPageChangeListener(viewListener);


        title=findViewById(R.id.tit);
        imageurl=findViewById(R.id.imageurl);
        share_un=findViewById(R.id.share_unshare);
        mSlideViewPager.setAdapter(slideradapter);
        addDotsIndicator(0);


        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void addDotsIndicator(int position)
    {
        mDots=new TextView[count];
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
            mDots[position%count].setTextColor(ContextCompat.getColor(this, R.color.White));
        }
    }


    ViewPager.OnPageChangeListener viewListener=new ViewPager.SimpleOnPageChangeListener()
    {
        @Override
        public void onPageSelected(int i) {
            Log.d("counting","c"+count);
            Log.d("i-","i-"+i);
            if (i < count) {
                i = i % count;
                addDotsIndicator(i);
                mcurrentpg = i;
              Log.d("count","c"+count);
              Log.d("i-","i-"+i);
              if(i==0)
              {
                  title.setText("Sample");
                  share_un.setText("Share/Not Share");
                  reason_response=null;
              }
                if (i == count - 1) {
                    nextButton.setEnabled(true);
                    nextButton.setVisibility(View.VISIBLE);

                        Log.d("hey","s");
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ResponseActivity.this, DashboardActivity.class);
                            intent.putExtra("response_id",0);
                            intent.putExtra("response_id_null",0);
                            intent.putExtra("mapid",a);
                            startActivity(intent);
                            finish();
                        }
                    });
                    nextButton.setText("Finish");
                }
                else {
                    Log.d("count2","c"+count);
                    Log.d("i2-","i-"+i);
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                }
                RadioButton fake = findViewById(R.id.fake);
                RadioButton notfake = findViewById(R.id.notfake);
                RadioButton not = findViewById(R.id.not);
                RadioButton notappealing = findViewById(R.id.notappealing);
                fake.setChecked(false);
                notfake.setChecked(false);
                not.setChecked(false);
                notappealing.setChecked(false);


                if (i != 0) {
                    Log.d("ii","i"+i);
                    Log.d("page", "j" + j);
                    if (common[array[i]] == 1)
                        share_un.setText("Share");
                    else
                        share_un.setText("Not share");
                    title.setText(Titles.get(array[i] + 2));
                    Glide.with(ResponseActivity.this)
                            .load(Urls.get(array[i]))
                            .placeholder(R.drawable.news)
                            .into(imageurl);
                    if (common[array[i]] == 1) {

                        FirebaseDatabase.getInstance().getReference("Share/" + reason_response).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                value = Integer.valueOf(dataSnapshot.getValue().toString()) + 1;
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        FirebaseDatabase.getInstance().getReference("Share/" + reason_response).setValue(value);

                    } else {
                        FirebaseDatabase.getInstance().getReference("Not Share/" + reason_response).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                value = Integer.valueOf(dataSnapshot.getValue().toString()) + 1;
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        FirebaseDatabase.getInstance().getReference("Not Share/" + reason_response).setValue(value);
                    }

                }
                else {
                    share_un.setText("Share or Unshare");
                    title.setText("Headlines");
                    Glide.with(ResponseActivity.this)
                            .load("")
                            .placeholder(R.drawable.news)
                            .into(imageurl);
                }


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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.fake:
                if (checked)
                    reason_response="Fake";
                break;
            case R.id.notfake:
                if (checked)
                    reason_response="Not Fake";
                break;
            case R.id.notappealing:
                if (checked)
                    reason_response="Not Appealing";
                break;
            case R.id.not:
                if (checked)
                    reason_response="None";
                break;
        }
    }



}
