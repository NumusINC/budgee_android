package com.numus.budgee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    //TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ExpensesFragment fragmentA = new ExpensesFragment();
        FragmentTransaction fragTransA = getSupportFragmentManager().beginTransaction();
        fragTransA.replace(R.id.frame,fragmentA,"ExpensesFragment");
        fragTransA.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_expenses:
                    //mTextMessage.setText(R.string.title_expenses);
                    ExpensesFragment fragmentA = new ExpensesFragment();
                    FragmentTransaction fragTransA = getSupportFragmentManager().beginTransaction();
                    fragTransA.replace(R.id.frame,fragmentA,"ExpensesFragment");
                    fragTransA.commit();
                    return true;
                case R.id.navigation_summary:
                    //mTextMessage.setText(R.string.title_summary);
                    SummaryFragment fragmentB = new SummaryFragment();
                    FragmentTransaction fragTransB = getSupportFragmentManager().beginTransaction();
                    fragTransB.replace(R.id.frame,fragmentB,"SummaryFragment");
                    fragTransB.commit();
                    return true;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    ProfileFragment fragmentC = new ProfileFragment();
                    FragmentTransaction fragTransC = getSupportFragmentManager().beginTransaction();
                    fragTransC.replace(R.id.frame,fragmentC,"ProfileFragment");
                    fragTransC.commit();
                    return true;
                case R.id.navigation_settings:
                    //mTextMessage.setText(R.string.title_settings);
                    SettingsFragment fragmentD = new SettingsFragment();
                    FragmentTransaction fragTransD = getSupportFragmentManager().beginTransaction();
                    fragTransD.replace(R.id.frame,fragmentD,"SettingsFragment");
                    fragTransD.commit();
                    return true;
            }
            return false;
        }
    };

}
