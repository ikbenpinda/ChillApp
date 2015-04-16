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
        public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

        public static final Home newInstance(String message)
        {
            Home f = new Home();
            Bundle bdl = new Bundle(1);
            bdl.putString(EXTRA_MESSAGE, message);
            f.setArguments(bdl);
            return f;
        }

        public Home(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            String message = getArguments().getString(EXTRA_MESSAGE);
            View v = inflater.inflate(R.layout.fragment_home, container, false);
            TextView messageTextView = (TextView)v.findViewById(R.id.textview);
            messageTextView.setText(message);

            return v;
        }
}