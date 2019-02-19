package com.example.tinder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTask extends AppCompatActivity {
    private Button mDashboard,mProfile,mPlay,mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_task);


        mDashboard = (Button) findViewById(R.id.dashboard);
        mDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, DashboardActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        mProfile = (Button) findViewById(R.id.profile);
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        mPlay = (Button) findViewById(R.id.play);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        mSettings = (Button) findViewById(R.id.settings);
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
