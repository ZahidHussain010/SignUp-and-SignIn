package com.example.gulraiztariq.signupdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    String name,email,pass,Gender;
    private TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        info = findViewById(R.id.data);
        Intent it = getIntent();
        name=it.getStringExtra("UName");
        email=it.getStringExtra("UEmail");
        pass=it.getStringExtra("UPassword");
        Gender = it.getStringExtra("Gender");
        info.setText("Your Name is "+name+"\nYour Email is  "+email+"\nYour Password is "+pass+"\nYour gender is "+Gender);
    }
}
