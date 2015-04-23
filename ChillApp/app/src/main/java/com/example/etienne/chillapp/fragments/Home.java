package com.example.etienne.chillapp.fragments;

import android.app.Activity;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.etienne.chillapp.R;
import com.example.etienne.chillapp.classes.Appointment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
        public static final String TITLE = "title";
        public ListView suggestions;
        public ListAdapter adapter;
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
            suggestions = (ListView) v.findViewById(R.id.home_listview);

            String[] values = new String[]{
                    "SUSHI - Eindhoven, met Hang",
                    "BOWLEN - Veghel, met Vrienden",
                    "FILM - Den Bosch, met Familie",
                    "KARTEN - Den Bosch, met Familie",
                    "FILM - Eindhoven, met Stef",
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity().getBaseContext(),
                    android.R.layout.simple_list_item_1,
                    values
            );
            if (suggestions != null)
                suggestions.setAdapter(adapter);
            //TextView messageTextView = (TextView)v.findViewById(R.id.textView);
            //messageTextView.setText(message);

            return v;
        }


}