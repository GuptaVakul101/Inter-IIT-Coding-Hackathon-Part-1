package com.example.coding_hackaton_guwahati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ContractorHomeActivity extends AppCompatActivity {


    Toolbar mTopToolbar;
    TabLayout tabLayout;
    int numTabs = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_home);

        mTopToolbar = findViewById(R.id.my_toolbar);
        mTopToolbar.setTitle("Name of the app");
        setSupportActionBar(mTopToolbar);

        tabLayout = findViewById(R.id.tab_layout);
        for(int i = 0; i < numTabs; i++){
            tabLayout.addTab(tabLayout.newTab());
        }


        //Initializing viewPager
        //This is our viewPager
        ViewPager viewPager = findViewById(R.id.pager);

        //Creating our pager adapter
        ContractorPager adapter = new ContractorPager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Home");

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        setResult(RESULT_OK);
        finish();
    }
}