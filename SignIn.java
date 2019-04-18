package com.example.gulraiztariq.signupdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    private EditText uemail ,upassword;
    private CheckBox remember;
    private Button signIn;
    private int time=3000;
    private ProgressBar progressBar;
    private boolean saveLogin;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private TextView signup;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        uemail = findViewById(R.id.txtEmail);
        upassword = findViewById(R.id.txtPassword);
        remember = findViewById(R.id.remember);
        signIn = findViewById(R.id.btnSignIn);
        progressBar = findViewById(R.id.probar);
        signup = findViewById(R.id.signup);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        progressBar.setVisibility(View.INVISIBLE);

        SignInButton();

        saveLogin = loginPreferences.getBoolean("saveLogin",false);

        if(saveLogin==true)
        {
            uemail.setText(loginPreferences.getString("email",""));
            upassword.setText(loginPreferences.getString("password",""));
            remember.setChecked(true);
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignIn.this,MainActivity.class);
                startActivity(it);
            }
        });

    }
    public void SignInButton()
    {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Validation();
                        progressBar.setVisibility(View.GONE);
                    }

                }, time);



                email = uemail.getText().toString();
                password = upassword.getText().toString();

                if (remember.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("email",email);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
            }
        });
    }

    public boolean validateEmail()
    {
        String InputEmail = uemail.getText().toString().trim();
        if(InputEmail.isEmpty())
        {
            uemail.setError("Enter your Email");
            return false;
        }
        else
        {
            uemail.setError(null);
            return true;
        }
    }
    public boolean validatePassword()
    {
        String InputPass = upassword.getText().toString().trim();
        if(InputPass.isEmpty())
        {
            upassword.setError("Enter your password");
            return false;
        }
        else
        {
            upassword.setError(null);
            return true;
        }
    }

    public void Validation() {
        if (!validateEmail() | !validatePassword()) {
            return;
        }


        Intent it = new Intent(SignIn.this,GetSignInData.class);
        String Email = uemail.getText().toString();
        String Password = upassword.getText().toString();
        it.putExtra("UEmail",Email);
        it.putExtra("UPassword",Password);
        startActivity(it);

    }
    public void timeforshow(long time)
    {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

}
