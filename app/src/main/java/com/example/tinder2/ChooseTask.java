package com.example.tinder2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ChooseTask extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 2818;
    private Button mDashboard, mProfile, mPlay, mSettings;
    String url;
    int x;
    Uri uri;
    TextView nameuser, walletuser, pagetitle, pagesubtitle;
    ProgressBar prbar;
    FirebaseAuth mAuth;
    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3, profilepic;
    ImageButton iclist, icapps, icplug, icnet;
    Dialog myDialog;

    private Button btnChoose, btnUpload;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_task);


        myDialog = new Dialog(this);


        final int x = (int) getIntent().getSerializableExtra("mapid");
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);
        mAuth = FirebaseAuth.getInstance();
        nameuser = findViewById(R.id.nameuser);
        prbar = findViewById(R.id.progress_bar);


        imageView3 = findViewById(R.id.imageView3);



        //Toast.makeText(this, mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();

        icapps = (ImageButton) findViewById(R.id.settings);
        icapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_out);
                icapps.startAnimation(animation);
                Intent intent = new Intent(ChooseTask.this, SettingsActivity.class);
                intent.putExtra("mapid", x);
                startActivity(intent);

                finish();

                return;
            }
        });
        icnet = findViewById(R.id.dashboard);
        icnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_out);
                icnet.startAnimation(animation);
                Intent intent = new Intent(ChooseTask.this, DashboardActivity.class);
                startActivity(intent);

                return;
            }
        });
        icplug = findViewById(R.id.profile);
        icplug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_out);
                icplug.startAnimation(animation);
                Intent intent = new Intent(ChooseTask.this, ProfileActivity.class);
                startActivity(intent);

                return;
            }
        });
        iclist = findViewById(R.id.play);
        iclist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_out);
                iclist.startAnimation(animation);
                Intent intent = new Intent(ChooseTask.this, NewsActivity.class);
                intent.putExtra("mapid", x);
                startActivity(intent);
                finish();


                return;
            }
        });


        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        btnguide = findViewById(R.id.btnguide);

        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_in);
                btnguide.startAnimation(animation);
                Intent a = new Intent(ChooseTask.this, GuideActivity.class);
                startActivity(a);
            }
        });

        // pass an animation
        imageView3.startAnimation(atg);

        pagetitle.startAnimation(atgtwo);
        pagesubtitle.startAnimation(atgtwo);

        btnguide.startAnimation(atgthree);
        final TextView topname=findViewById(R.id.nameuser);
        final ImageView userimg=findViewById(R.id.topuserpic);


        //Adding name in choose Activity
        FirebaseDatabase.getInstance().getReference("users/" + mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("suthar", dataSnapshot.toString());
                String name = dataSnapshot.child("name").getValue(String.class);
                String url = dataSnapshot.child("url").getValue(String.class);
                topname.setText("Hi "+name);


                Glide.with(ChooseTask.this)
                        .load(url)
                        .apply(RequestOptions.circleCropTransform())
                        .into(userimg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtclose = myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        btnChoose = myDialog.findViewById(R.id.choose);

        imageView = myDialog.findViewById(R.id.userpic);
        final TextView mName = myDialog.findViewById(R.id.name);
        final TextView mAge = myDialog.findViewById(R.id.age);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        FirebaseDatabase.getInstance().getReference("users/" + mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("suthar", dataSnapshot.toString());
                String name = dataSnapshot.child("name").getValue(String.class);
                String age = dataSnapshot.child("age").getValue(String.class);
                String url = dataSnapshot.child("url").getValue(String.class);

                mName.setText(name);
                mAge.setText(age+" yrs");

                Glide.with(ChooseTask.this)
                        .load(url)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadImage();
//            }
//        });
//
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            // Glide.with(this).load(filePath).into(imageView);
            uploadImage(filePath);
        }
    }

    private void uploadImage(Uri uri) {

        final StorageReference ref = FirebaseStorage.getInstance().getReference(mAuth.getUid()).child(uri.getLastPathSegment());
        UploadTask uploadTask = ref.putFile(uri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.d("suthar", downloadUri.toString());
                    Glide.with(ChooseTask.this)
                            .load(downloadUri)
                            .apply(RequestOptions.circleCropTransform())
                            .into(imageView);
                    saveImageUrlToDatabase(downloadUri.toString());
                } else {
                    Log.d("suthar", "Error");
                    // Handle failures
                    // ...
                }
            }
        });
    }

    private void saveImageUrlToDatabase(String url) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("url", url);
        FirebaseDatabase.getInstance().getReference("users/" + mAuth.getUid()).updateChildren(map);
    }





}



