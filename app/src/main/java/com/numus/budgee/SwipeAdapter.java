package com.numus.budgee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new TransactionsFragment();
                break;
            case 1:
                fragment = new SummaryFragment();
                break;
            case 2:
                fragment = new ProfileFragment();
                break;
            case 3:
                fragment = new SettingsFragment();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber", i+1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
