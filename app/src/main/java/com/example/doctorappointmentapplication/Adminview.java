package com.example.doctorappointmentapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.DateTime;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Adminview extends AppCompatActivity {

    public static final String TAG = null;
       EditText settxt,settimeslot;
       Button setaptbtn,checkapt;
       FirebaseAuth fauth;
       FirebaseFirestore fstore;
      // EditText vPwd,vEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);
        settxt = findViewById(R.id.datechoice);
        settimeslot = findViewById(R.id.timeslot);
        setaptbtn = findViewById(R.id.setapt);
        checkapt = findViewById(R.id.vwapt);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        //vEmail = findViewById(R.id.Email);
        //vPwd = findViewById(R.id.Name);



        setaptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String TimeSlot = settimeslot.getText().toString();
                String avlapointmnet = settxt.getText().toString();
                System.out.println("This is the lenghth:"+avlapointmnet.length());

               // String userid = fauth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("avaiableapointment").document();
                Map<String, Object> aptdetails = new HashMap<>();


                           aptdetails.put("apoitmentdate", avlapointmnet+" "+TimeSlot);
                           //aptdetails.put("TimeSlot", TimeSlot);
                           //System.out.println(userid);



                documentReference.set(aptdetails).addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void avoid) {

                        Log.d(TAG, "user data inserted firebase for user id" + avlapointmnet);

                    }

                });











            }
        });


        settxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);


                DatePickerDialog picker = new DatePickerDialog(Adminview.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        settxt.setText(day + "/" + (month + 1) + "/" + year);


                    }
                }, year, month, day);
                picker.show();
            }
        });



        settimeslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timepicker = new TimePickerDialog(Adminview.this, new TimePickerDialog.OnTimeSetListener() {


                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        String DateTime = "";
                        if(hour>=0 && hour<12){

                            DateTime = hour + ":" + minute + " AM";
                        } else {
                            if(hour == 12){
                                DateTime = hour + ":" + minute + " PM";
                            } else{
                                hour = hour -12;
                                DateTime = hour + ":" + minute + " PM";
                            }
                        }

                        settimeslot.setText(DateTime);
                    }

                    },hour,minute,true);
                timepicker.show();




            }


        });

    }}