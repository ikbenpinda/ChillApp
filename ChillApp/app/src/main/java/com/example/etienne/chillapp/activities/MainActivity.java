package com.example.etienne.chillapp.activities;

import java.util.*;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.etienne.chillapp.R;
import com.example.etienne.chillapp.fragments.Appointments;
import com.example.etienne.chillapp.fragments.Contacts;
import com.example.etienne.chillapp.fragments.Home;
import com.example.etienne.chillapp.fragments.Suggestions;
import com.facebook.FacebookSdk;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener,
        Appointments.OnFragmentInteractionListener, Contacts.OnFragmentInteractionListener{

    MyPageAdapter pageAdapter;
    ViewPager pager;
    ActionBar actionbar;
    PagerTabStrip tabstrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        tabstrip = new PagerTabStrip(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
        FacebookSdk.sdkInitialize(getApplicationContext());

        if (getPreferences(0).getBoolean("FirstUse", true)){
            Intent introductions = new Intent(getBaseContext(), Introduction.class);
            introductions.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getBaseContext().startActivity(introductions);
            getPreferences(0).edit().putBoolean("FirstUse", false).commit();
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Custom adapter for fragments.
     */
    class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return getFragments().get(position).getArguments().get("title").toString();
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

    class TabsPagerAdapter extends FragmentPagerAdapter {

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {
            return getFragments().get(index);
        }

        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return getFragments().size();
        }

    }

    /**
     * Sets the tabs on the main menu.
     * @return
     */
    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        Fragment f1 = Appointments.newInstance("Afspraken");
        Fragment f2 = Home.newInstance("Home");
        Fragment f3 = Contacts.newInstance("Contacts");
        fList.add(f1);
        fList.add(f2);
        fList.add(f3);
        return fList;
    }

}

