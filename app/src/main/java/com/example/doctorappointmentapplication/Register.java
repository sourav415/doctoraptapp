package com.example.doctorappointmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = null;
    EditText vFullname,vMob,vAge,vPwd,vEmail;
    Button vRegisterbtn;
    TextView vlogin;
    FirebaseAuth vAuth;
    FirebaseFirestore fstore;
    String userid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        vFullname = findViewById(R.id.fullName);
        vMob = findViewById(R.id.Mob);
        vEmail = findViewById(R.id.Email);
        vAge = findViewById(R.id.Age);
        vPwd = findViewById(R.id.Name);
        vRegisterbtn = findViewById(R.id.Register);
        vlogin = findViewById(R.id.alraedyregister);
        vAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();



        vRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = vFullname.getText().toString().trim();
                String mob = vMob.getText().toString();
                String password = vPwd.getText().toString().trim();
                String age = vAge.getText().toString().trim();
                String email = vEmail.getText().toString().trim();

                if(TextUtils.isEmpty(fullname)){

                    vFullname.setError("Name is Required.");
                    return;
                }

                if(TextUtils.isEmpty(mob)){

                    vMob.setError("Mobile No: is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    vPwd.setError("password is Required.");
                    return;
                }

                if(TextUtils.isEmpty(age)){

                    vAge.setError("age is Required.");
                    return;
                }

                if(password.length()<6){

                    vPwd.setError("Password must be greater than 6 character.");
                    return;
                }

                if(TextUtils.isEmpty(fullname)){

                    vEmail.setError("Email is Required.");
                    return;
                }

                vAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User is Registered!! ", Toast.LENGTH_SHORT).show();
                            userid = vAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userid);
                            Map<String,Object> userdetails = new HashMap<>();
                            userdetails.put("fullname",fullname);
                            userdetails.put("Mobile",mob);
                            userdetails.put("pwd",password);
                            userdetails.put("age",age);
                            userdetails.put("Email",email);
                            documentReference.set(userdetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void avoid) {

                                    Log.d(TAG,"user data inserted firebase for user id"+userid) ;

                                }
                            });



                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                        else{

                            Toast.makeText(Register.this, "Some Error is occured"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        vlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}