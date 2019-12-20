package com.example.coding_hackaton_guwahati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_USER_SIGNUP = 0;
    private static final int REQUEST_USER_HOME = 1;
    private static final int REQUEST_CONTRACTOR_SIGNUP = 3;

    TextView signupUserLink, signupContractorLink;
    EditText emailText, passwdText;
    Button loginBtn;

    int minPassLength = 6;
    int maxPassLength = 16;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test=findViewById(R.id.tst_btn);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyFragment fragment = new MyFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fra, fragment);
//                transaction.commit();
//            }
//        });

        signupUserLink = findViewById(R.id.signup_user_link);
        signupContractorLink = findViewById(R.id.signup_contractor_link);
        emailText = findViewById(R.id.input_email);
        passwdText = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.btn_login);

        signupUserLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserSignupActivity.class);
                startActivityForResult(intent, REQUEST_USER_SIGNUP);
            }
        });

        signupContractorLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContractorSignupActivity.class);
                startActivityForResult(intent, REQUEST_CONTRACTOR_SIGNUP);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            Intent intent = new Intent(getApplicationContext(), UserHomeActivity.class);
            startActivityForResult(intent, REQUEST_USER_HOME);
        }
    }

    public void login() {

        if(!validate()){
            return;
        }

        loginBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwdText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            onLoginSuccess();
                            progressDialog.dismiss();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            onLoginFailed();
                            progressDialog.dismiss();
                            updateUI(null);
                        }

                    }
                });
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwdText.getText().toString();


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        }
        else{
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < minPassLength || password.length() > maxPassLength) {
            passwdText.setError("between " + minPassLength + " and " + maxPassLength + " alphanumeric characters");
            valid = false;
        } else {
            passwdText.setError(null);
        }

        if(!valid){
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        }

        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_USER_SIGNUP) {
            if (resultCode == RESULT_OK) {

            }
        }
        else if(requestCode == REQUEST_USER_HOME){
            if(resultCode == RESULT_OK){
                
            }
        }
        else if(requestCode == REQUEST_CONTRACTOR_SIGNUP){
            if(resultCode == RESULT_OK){

            }
        }
    }

    public void onLoginSuccess() {
        loginBtn.setEnabled(true);
        Toast.makeText(getBaseContext(), "Login successful", Toast.LENGTH_LONG).show();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginBtn.setEnabled(true);

    }
}
