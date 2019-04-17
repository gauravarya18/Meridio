package com.example.tinder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    int a;
    TextView textView3,textView4,textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        int share_1[]=(int[])getIntent().getSerializableExtra("share");
        int score=(int)getIntent().getSerializableExtra("score");
        int shared_level2=(int)getIntent().getSerializableExtra("shared_level2");
        int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;
        int shared_level1=0;
        for(int i=0;i<share_1.length;i++)
        {
            if(share_1[i]==1)
                shared_level1++;

        }
       textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
      textView3.setText(shared_level1);
      textView4.setText(shared_level2);
      textView5.setText("Your Final Score is "+score);
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(DashboardActivity.this, ChooseTask.class);
        intent.putExtra("mapid",a);
        startActivity(intent);
    }
}
