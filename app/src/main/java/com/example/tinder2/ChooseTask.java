package com.example.tinder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTask extends AppCompatActivity {
    private Button mDashboard,mProfile,mPlay,mSettings;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_task);
      int i=(int)getIntent().getSerializableExtra("mapid");
         x=i;
        mDashboard = (Button) findViewById(R.id.dashboard);
        mDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, DashboardActivity.class);
                startActivity(intent);

                return;
            }
        });
        mProfile = (Button) findViewById(R.id.profile);
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, ProfileActivity.class);
                startActivity(intent);

                return;
            }
        });
        mPlay = (Button) findViewById(R.id.play);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, NewsActivity.class);
                intent.putExtra("mapid",x);
                startActivity(intent);

                return;
            }
        });
        mSettings = (Button) findViewById(R.id.settings);
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, SettingsActivity.class);
                startActivity(intent);

                return;
            }
        });
    }
}
