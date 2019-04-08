package com.example.tinder2;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

public class bootActivity extends AppCompatActivity {
    Handler handler;
    ImageView bgapp, clover;
    LinearLayout textsplash, texthome;
    ConstraintLayout login;
    Animation frombottom;


    private Button mLogin;
    private EditText mEmail, mPassword;
    private TextView mRegister;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        login =(ConstraintLayout) findViewById(R.id.login_page);



        texthome.startAnimation(frombottom);
        login.startAnimation(frombottom);




        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user==null) {
                    bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(500);
                    clover.animate().alpha(0).setDuration(800).setStartDelay(800);
                    textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(800);
                }
                else
                {
                    bgapp.animate().translationY(-3000).setDuration(2000).setStartDelay(800);
                    clover.animate().alpha(0).setDuration(2000).setStartDelay(800);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        Log.d("gaurav","Error404");
                    }
                    catch (InterruptedException e)
                    {
                       // Log.d("gaurav","Error404");
                    }

                }

                if (user !=null){



                    String putName=user.getDisplayName();
                    Toast.makeText(bootActivity.this, putName, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(bootActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        mLogin = (Button) findViewById(R.id.login);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user!=null)

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_out);
                mLogin.startAnimation(animation);
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                if(email.isEmpty())
                {
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }

                if(email.isEmpty())
                {
                    mPassword.setError("Password is required");
                    mPassword.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    mPassword.setError("Minimum length of password must be 6");
                    mPassword.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(bootActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(bootActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mRegister = (TextView) findViewById(R.id.register);



        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bootActivity.this, RegistrationActivity.class);
                startActivity(intent);

                return;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }

}

