package com.example.doctorappointmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText vEmail,vPwd;
    Button vLogin;
    TextView vCreateAccount;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vEmail = findViewById(R.id.Email);
        vPwd = findViewById(R.id.Name);
        vLogin = findViewById(R.id.login);
        vCreateAccount = findViewById(R.id.createaccount);
        fAuth = FirebaseAuth.getInstance();
        vLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = vEmail.getText().toString().trim();
                String password = vPwd.getText().toString().trim();


                if(TextUtils.isEmpty(email)){

                    vEmail.setError("Mobile NO: is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    vPwd.setError("password is Required.");
                    return;
                }

                //authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "login is Successful!! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else{

                            Toast.makeText(Login.this, "Some Error is happening!!" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });

        vCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}