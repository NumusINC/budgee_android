package com.numus.budgee;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FloatingActionButton myFab = findViewById(R.id.floatingActionButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        CustomViewPager viewPager = findViewById(R.id.view_pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        viewPager.setPagingEnabled(false);
        viewPager.setCurrentItem(0);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            CustomViewPager viewPager = findViewById(R.id.view_pager);
            SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
            switch (item.getItemId()) {
                case R.id.navigation_expenses:
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(0);

                    return true;
                case R.id.navigation_summary:
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(1);

                    return true;

            }
            return false;
        }
    };

}
