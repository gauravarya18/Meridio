package com.example.tinder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
FirebaseAuth mAuth=FirebaseAuth.getInstance();
Button chngln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        chngln=findViewById(R.id.changelang);
        chngln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingsActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();

                return;
            }
        });

    }
    public void logoutUser (View view){
        mAuth.signOut();
        Intent intent = new Intent(SettingsActivity.this, bootActivity.class);
        startActivity(intent);
        finish();

        return;
    }

}
