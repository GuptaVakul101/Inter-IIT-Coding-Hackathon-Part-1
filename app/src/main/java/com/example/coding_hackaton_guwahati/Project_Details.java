package com.example.coding_hackaton_guwahati;


import androidx.appcompat.app.AppCompatActivity;
import com.example.coding_hackaton_guwahati.Prevalent;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Project_Details extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project__details);

        MapsFragment fragInfo = new MapsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.maps_fragment, fragInfo).commit();
    }
}
