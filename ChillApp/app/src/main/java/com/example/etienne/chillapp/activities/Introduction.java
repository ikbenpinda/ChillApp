package com.example.etienne.chillapp.activities;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.etienne.chillapp.R;
import com.example.etienne.chillapp.fragments.Introduction_1;
import com.example.etienne.chillapp.fragments.Introduction_2;
import com.example.etienne.chillapp.fragments.Introduction_3;
import com.example.etienne.chillapp.utilities.customFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Introduction pages for first time use of app.
 * @author Etienne
 */
public class Introduction extends FragmentActivity implements Introduction_1.OnFragmentInteractionListener, Introduction_2.OnFragmentInteractionListener, Introduction_3.OnFragmentInteractionListener{

    customFragmentPagerAdapter adapter;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        // Add introduction panels.
        ArrayList<Fragment>fragments = new ArrayList<>();
        fragments.add(Introduction_1.newInstance("hello","world"));
        fragments.add(Introduction_2.newInstance("hello", "world"));
        fragments.add(Introduction_3.newInstance("hello", "world"));
        // Set adapter.
        adapter = new customFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_introduction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
