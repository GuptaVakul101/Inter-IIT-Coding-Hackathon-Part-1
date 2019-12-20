package com.example.coding_hackaton_guwahati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_SIGNUP = 0;

    TextView signupUserLink;
    EditText emailText, passwdText;
    Button loginBtn;

    int MinPasswdLength = 4;
    int MaxPasswdLength = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupUserLink = findViewById(R.id.signup_user_link);
        emailText = findViewById(R.id.input_email);
        passwdText = findViewById(R.id.input_password);

        signupUserLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserSignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
}
