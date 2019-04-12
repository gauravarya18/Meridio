package com.example.tinder2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        final TextView mName=findViewById(R.id.name);
        final TextView mAge=findViewById(R.id.age);
        final ImageView img=findViewById(R.id.profile_image);
        final TextView mGender=findViewById(R.id.gender);
        FirebaseDatabase.getInstance().getReference("users/" + user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("suthar", dataSnapshot.toString());
                String name = dataSnapshot.child("name").getValue(String.class);
                String number = dataSnapshot.child("age").getValue(String.class);
                String url = dataSnapshot.child("url").getValue(String.class);
                String gender =dataSnapshot.child("gender").getValue().toString();
                mAge.setText(number);
                mName.setText(name);
                mGender.setText(gender);

                Glide.with(ProfileActivity.this)
                        .load(url)
                        .placeholder(R.drawable.profile_ic)
                        .apply(RequestOptions.circleCropTransform())
                        .into(img);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
