package com.example.tinder2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private Button mRegister;
    private EditText mEmail, mPassword;
    private EditText mContact,mName;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
//                    Intent intent = new Intent(RegistrationActivity.this, MapsActivity.class);
//                    startActivity(intent);
                    finish();
//                    return;
                }
            }
        };


        mRegister = (Button) findViewById(R.id.register);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);

        mName= (EditText) findViewById(R.id.name);
        mContact=(EditText) findViewById(R.id.contact);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String name=mName.getText().toString();
                final String contact=mContact.getText().toString();
                if(email.isEmpty())
                {
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }
                if(name.isEmpty())
                {
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }
                if(contact.isEmpty())
                {
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }
                if(password.isEmpty())
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
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(RegistrationActivity.this, "You are already registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this,task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {

                            String userId=mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("name");
                            currentUserDb.setValue(name);
                            DatabaseReference currentUserDb1 = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("contact");
                            currentUserDb1.setValue(contact);

                            UserProfileChangeRequest profile =new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                                    .build();

//                            Intent intent=new Intent(RegistrationActivity.this,MapsActivity.class);
//                            startActivity(intent);
//                            finish();




                        }
                    }
                });
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