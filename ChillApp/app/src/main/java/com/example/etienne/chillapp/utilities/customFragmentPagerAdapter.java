package com.example.etienne.chillapp.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * Custom implementation of FragmentPagerAdapter to prevent code cluttering.
 * @author Etienne
 */
public class customFragmentPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragments;

    /**
     * Creates a new fragmentPagerAdapter with overridden methods for easier use.
     */
    public customFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
