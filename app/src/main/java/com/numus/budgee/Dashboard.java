package com.numus.budgee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    //TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FloatingActionButton myFab = findViewById(R.id.floatingActionButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("***********************");
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
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
                case R.id.navigation_profile:
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(2);

                    return true;
                case R.id.navigation_settings:
                    viewPager.setAdapter(swipeAdapter);
                    viewPager.setCurrentItem(3);

                    return true;
            }
            return false;
        }
    };

}
