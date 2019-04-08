package com.example.tinder2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


import java.io.IOException;

public class ChooseTask extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 2818 ;
    private Button mDashboard,mProfile,mPlay,mSettings;
    String url;
    int x;
    Uri uri;
    TextView nameuser, walletuser,pagetitle, pagesubtitle;
    ProgressBar prbar;
   FirebaseAuth mAuth;
    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3,profilepic;
    ImageButton iclist,icapps,icplug,icnet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_task);
        final int x= (int)getIntent().getSerializableExtra("mapid");
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);
    mAuth=FirebaseAuth.getInstance();
        nameuser = findViewById(R.id.nameuser);
        prbar = findViewById(R.id.progress_bar);
//        walletuser = findViewById(R.id.walletuser);

        imageView3 = findViewById(R.id.imageView3);
        profilepic=(ImageView) findViewById(R.id.imageView2);
//        profilepic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showImageChooser();
//
//                return;
//            }
//        });


        icapps=(ImageButton) findViewById(R.id.settings);
        icapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, SettingsActivity.class);
                startActivity(intent);

                return;
            }
        });
        icnet=findViewById(R.id.dashboard);
        icnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, DashboardActivity.class);
                startActivity(intent);

                return;
            }
        });
        icplug=findViewById(R.id.profile);
        icplug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, ProfileActivity.class);
                startActivity(intent);

                return;
            }
        });
        iclist=findViewById(R.id.play);
        iclist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTask.this, NewsActivity.class);
                intent.putExtra("mapid",x);
                startActivity(intent);

                return;
            }
        });





        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        btnguide = findViewById(R.id.btnguide);

        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ChooseTask.this,GuideActivity.class);
                startActivity(a);
            }
        });

        // pass an animation
        imageView3.startAnimation(atg);

        pagetitle.startAnimation(atgtwo);
        pagesubtitle.startAnimation(atgtwo);

        btnguide.startAnimation(atgthree);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==CHOOSE_IMAGE && requestCode==RESULT_OK && data!=null && data.getData()!=null)
//        {
//
//            uri=data.getData();
//
//            try {
//                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                profilepic.setImageBitmap(bitmap);
//                uploadImage();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

//    private void uploadImage() {
//        final StorageReference profileImageRef= FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");
//          if(uri!=null)
//          {   prbar.setVisibility(View.VISIBLE);
//              profileImageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                  @Override
//                  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                      prbar.setVisibility(View.GONE);
//                       url= profileImageRef.getDownloadUrl().toString();
//                       saveuser();
//
//                  }
//
//                  private void saveuser() {
//                      FirebaseUser user=mAuth.getCurrentUser();
//                      if(user!=null&&url!=null)
//                      {
//                          UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
//                                  .setPhotoUri(Uri.parse(url))
//                                  .build();
//                          user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
//                              @Override
//                              public void onComplete(@NonNull Task<Void> task) {
//                                  if(task.isSuccessful())
//                                  {
//                                      Toast.makeText(ChooseTask.this, "Profile Updated", Toast.LENGTH_SHORT).show();
//                                  }
//                              }
//                          });
//
//
//                      }
//
//                  }
//              })
//                      .addOnFailureListener(new OnFailureListener() {
//                          @Override
//                          public void onFailure(@NonNull Exception e) {
//                              prbar.setVisibility(View.GONE);
//                          }
//                      });
//          }
//    }
//
//    private void  showImageChooser(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"select profile pic"), CHOOSE_IMAGE);
//   }

}



