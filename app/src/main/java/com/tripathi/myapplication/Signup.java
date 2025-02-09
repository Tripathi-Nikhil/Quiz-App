package com.tripathi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Signup extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText username, email, phone, password;
    TextView login;
    View progressbar;
    Button register;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        progressbar = findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(Signup.this, MainActivity.class));
            finishAffinity();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Signin.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memail = email.getText().toString();
                String mpassword = password.getText().toString();
                String musername = username.getText().toString();
                String mphone = phone.getText().toString();

                if(TextUtils.isEmpty(memail)){
                    email.setError("Please enter email");
                    return;
                }if(TextUtils.isEmpty(mpassword)){
                    password.setError("Please enter password");
                    return;
                }if(TextUtils.isEmpty(musername)){
                    username.setError("Please enter username");
                    return;
                }if(TextUtils.isEmpty(mphone)){
                    phone.setError("Please enter phone number");
                    return;
                }if(mpassword.length()<8){
                    password.setError("Password must be greater than 8 characters");
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(memail, mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser mUser = mAuth.getCurrentUser();
                            mUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Signup.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"On failure: Email not sent" + e.getMessage());
                                }
                            });
                            DocumentReference documentReference = mStore.collection("users").document(mUser.getUid());
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name", musername);
                            user.put("Email", memail);
                            user.put("Phone", mphone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "User Profile Created");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "On Failure:" + e.getMessage());
                                }
                            });
                            Toast.makeText(Signup.this, "User Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this, MainActivity.class));
                            finishAffinity();
                        }else{
                            Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}