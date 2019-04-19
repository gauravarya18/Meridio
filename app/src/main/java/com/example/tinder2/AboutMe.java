package com.example.tinder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutMe extends AppCompatActivity {
int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(AboutMe.this, ChooseTask.class);
        intent.putExtra("mapid",a);
        startActivity(intent);
    }

}
