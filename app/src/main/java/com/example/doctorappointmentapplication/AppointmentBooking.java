package com.example.doctorappointmentapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.collection.ArraySet;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AppointmentBooking extends AppCompatActivity {


    TextView TV;

    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_booking);
        TV = findViewById(R.id.idAvlApt);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

       // userid = fauth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("sam").document("MrtYjnJq7Mqs0fbbcAJS");


        documentReference.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {



           TV.setText(documentSnapshot.getString("name"));
            }
        });




        }


 }



