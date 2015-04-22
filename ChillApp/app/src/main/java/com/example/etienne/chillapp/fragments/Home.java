package com.example.etienne.chillapp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.etienne.chillapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
        public static final String TITLE = "title";

        public static final Home newInstance(String message)
        {
            Home fragment = new Home();
            Bundle bdl = new Bundle(1);
            bdl.putString(TITLE, message);
            fragment.setArguments(bdl);
            return fragment;
        }

        public Home(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            String message = getArguments().getString(TITLE);
            View v = inflater.inflate(R.layout.fragment_home, container, false);
            //TextView messageTextView = (TextView)v.findViewById(R.id.textView);
            //messageTextView.setText(message);

            return v;
        }
}