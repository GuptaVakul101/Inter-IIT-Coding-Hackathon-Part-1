package com.example.coding_hackaton_guwahati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
//    Button test;
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
    }
}
