package com.example.gulraiztariq.signupdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText name,email,password;
    private Button signup;
    private CheckBox checkBox;
    private ProgressBar pb;
    private int time=3000;
    private RadioGroup RbGroup;
    private RadioButton GenderRb,rb;
    private TextView txtaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb = findViewById(R.id.rbmale);
        RbGroup = findViewById(R.id.rbGroup);
        name = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        checkBox = findViewById(R.id.cbshow);
        signup = findViewById(R.id.btnsign);
        pb = findViewById(R.id.progrbar);
        pb.setVisibility(View.INVISIBLE);
        txtaccount=findViewById(R.id.signin);

        signup();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        haveaccount();

    }
    public void signup()
    {

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pb.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        validation();
                        pb.setVisibility(View.GONE);
                    }

                }, time);
            }
        });
    }

    public boolean validateEmail()
    {
         String InputEmail = email.getText().toString().trim();
         if(InputEmail.isEmpty())
         {
             email.setError("Enter your Email");
             return false;
         }
         else
         {
             email.setError(null);
             return true;
         }
    }
    public  boolean validateName()
    {
        String InputName = name.getText().toString().trim();
        if(InputName.isEmpty())
        {
            name.setError("Enter your Name");
            return false;
        }
        else
        {
            name.setError(null);
            return true;
        }
    }
    public boolean validatePassword()
    {
        String InputPass = password.getText().toString().trim();
        if(InputPass.isEmpty())
        {
            password.setError("Enter your password");
            return false;
        }
        else
        {
            password.setError(null);
            return true;
        }
    }
    public void validation()
    {
        if(!validateName() | !validateEmail()| !validatePassword())
        {
            return;
        }

        Intent it = new Intent(MainActivity.this,NextActivity.class);
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        it.putExtra("UName",Name);
        it.putExtra("UEmail",Email);
        it.putExtra("UPassword",Password);

        int selectedid = RbGroup.getCheckedRadioButtonId();
        GenderRb = findViewById(selectedid);
        if(selectedid==-1)
        {
            rb.setError("Select your gender");
        }
        else
        {
            rb.setError(null);
            GenderRb.getText();
            it.putExtra("Gender",GenderRb.getText().toString());
        }

        startActivity(it);

        name.getText().clear();
        email.getText().clear();
        password.getText().clear();
    }
    public void haveaccount()
    {
        txtaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,SignIn.class);
                startActivity(it);
            }
        });
    }

}
