package com.example.gulraiztariq.signupdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GetSignInData extends AppCompatActivity {

    String email,pass;
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_sign_in_data);

        info = findViewById(R.id.getdata);

        Intent it = getIntent();
        email=it.getStringExtra("UEmail");
        pass=it.getStringExtra("UPassword");
        info.setText("Your Email is  "+email+"\nYour Password is "+pass);
    }
}
