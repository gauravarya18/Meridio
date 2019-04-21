package com.meridio.tinder2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;

public class ChooseTask extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 2818;
    private Button mDashboard, mProfile, mPlay, mSettings;
    String feed;
    EditText getfeedback;
    ImageButton sendit;
    int x;
    Uri uri;
    TextView nameuser, walletuser, pagetitle, pagesubtitle, tv;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    Button btnguide;
    Animation atg, atgtwo, atgthree, atgfour;
    ImageView imageView3, change;
    ImageButton iclist, icapps, icplug, icnet;
    Dialog myDialog, fdDialog;
    String m = "Male";
    TextView fdtv;
    int share[];
    String score0, shared_level10, shared_level20;
    String name="name";

    private Button btnChoose, btnUpload;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_task);

        myDialog = new Dialog(this);
        fdDialog = new Dialog(this);
        fdtv = findViewById(R.id.feedback);

        final int x = (int) getIntent().getSerializableExtra("mapid");
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);
        atgfour = AnimationUtils.loadAnimation(this, R.anim.atgfour);
        mAuth = FirebaseAuth.getInstance();
        nameuser = findViewById(R.id.nameuser);
        change = findViewById(R.id.editoptn);


//        fdtv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                feedback();
//            }
//        });

        imageView3 = findViewById(R.id.imageView3);

        //Setting region in textview with id tv
        tv = (TextView) findViewById(R.id.tv);
        if (x == 1)
            tv.setText("Latin America");
        else if (x == 2)
            tv.setText("North America");
        else if (x == 3)
            tv.setText("Australia");
        else if (x == 4)
            tv.setText("Asia");
        else if (x == 5)
            tv.setText("Africa");
        else if (x == 6)
            tv.setText("Arab");
        else if (x == 7)
            tv.setText("Europe");

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        icapps = (ImageButton) findViewById(R.id.settings);
        icapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_out);
                icapps.startAnimation(animation);
                Intent intent = new Intent(ChooseTask.this, AboutMe.class);
                intent.putExtra("mapid", x);
                startActivity(intent);


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
                intent.putExtra("mapid", x);
                intent.putExtra("response_id",0);
                intent.putExtra("response_id_null",0);


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
                mAuth.signOut();
                Intent intent = new Intent(ChooseTask.this, bootActivity.class);
                startActivity(intent);
                finish();


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
                intent.putExtra("level", 1);
                intent.putExtra("share", share);
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
        fdtv.startAnimation(atgfour);
        final TextView topname = findViewById(R.id.nameuser);
        final ImageView userimg = findViewById(R.id.topuserpic);


        //fetching score from database


        //Adding name in choose Activity
        FirebaseDatabase.getInstance().getReference("users/" + mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Log.d("suthar", dataSnapshot.toString());
                name = dataSnapshot.child("name").getValue(String.class);
                String url = dataSnapshot.child("url").getValue(String.class);
                feed = dataSnapshot.child("feedback").getValue(String.class);
                topname.setText("Hi, " + name.toUpperCase() + " !");

                if (!ChooseTask.this.isFinishing()) {
                    Glide.with(ChooseTask.this)
                            .load(url)
                            .placeholder(R.drawable.profile_ic)
                            .apply(RequestOptions.circleCropTransform())
                            .into(userimg);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void ShowPopup(View v) {
        TextView txtclose;

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
        final TextView mGender = myDialog.findViewById(R.id.genderpop);
        //final ProgressBar progressBar=myDialog.findViewById(R.id.simpleProgressBar);


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
                String gender = dataSnapshot.child("gender").getValue().toString();


                mName.setText(name);
                mAge.setText(age + " yrs");


                mGender.setText(gender);

                Log.d("suthar", url);


                Glide.with(ChooseTask.this)
                        .load(url)
                        .placeholder(R.drawable.profile_ic)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ChooseTask.this, "Error !", Toast.LENGTH_SHORT).show();

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
           // imageView.setImageURI(Uri.parse(filePath.toString()));
            uploadImage(filePath);
            //ProgressDialog(ChooseTask.this);

        }
    }

    private void uploadImage(Uri uri) {

        final ProgressDialog progressDialog = new ProgressDialog(ChooseTask.this);
        progressDialog.setMessage("Applying Changes" + "\n" + "Please wait..");
        progressDialog.show();


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
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 5000);

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


//    public void logoutUser(View view) {
//
//        return;
//    }

    public void feedback(View v) {
        TextView txtclose;

        fdDialog.setContentView(R.layout.feedback);
        txtclose = fdDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fdDialog.dismiss();
            }
        });
        fdDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        fdDialog.show();


        getfeedback = fdDialog.findViewById(R.id.writefeedback);


        ImageButton sendit = fdDialog.findViewById(R.id.send);
        sendit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = getfeedback.getText().toString();
                String userId = mAuth.getCurrentUser().getUid();
                if (!feedback.equals("")) {
                    feedback = feed + "/" + feedback;
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("feedback", feedback);

                    FirebaseDatabase.getInstance().getReference("users/" + userId).updateChildren(map);
                    Toast.makeText(ChooseTask.this, "Thanks for your valuable suggestion.", Toast.LENGTH_SHORT).show();
                    getfeedback.setText("");
                    fdDialog.dismiss();
                } else {
                    Toast.makeText(ChooseTask.this, "Please Enter Your Valuable Inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}



