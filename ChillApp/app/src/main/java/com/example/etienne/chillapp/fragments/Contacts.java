package com.example.etienne.chillapp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.etienne.chillapp.R;
import com.example.etienne.chillapp.activities.ContactAdapter;
import com.example.etienne.chillapp.activities.ContactBean;
import com.example.etienne.chillapp.activities.ContactsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Contacts.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contacts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contacts extends Fragment implements AdapterView.OnItemClickListener {

    Button button;

    private ListView mylistView;

    private List<ContactBean> mylist = new ArrayList<ContactBean>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public static final String TITLE = "title";
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param message Parameter 1.
     * @return A new instance of fragment Contacts.
     */
    // TODO: Rename and change types and number of parameters
    public static final Contacts newInstance(String message)
    {
        Contacts fragment = new Contacts();
        Bundle bdl = new Bundle(1);
        bdl.putString(TITLE, message);
        fragment.setArguments(bdl);
        return fragment;
    }

    public Contacts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // setContentView(R.layout.fragment_contacts);



        mylistView = (ListView) getView().findViewById(R.id.listviewshow);

        mylistView.setOnItemClickListener(this);



        Cursor phonescursor = container.getContext().getContentResolver().query(

                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,

                null, null);

        while (phonescursor.moveToNext()) {



            String name = phonescursor

                    .getString(phonescursor

                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));



            String phoneNumber = phonescursor

                    .getString(phonescursor

                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));



            ContactBean objContact = new ContactBean();

            objContact.Nameset(name);

            objContact.PhoneNoset(phoneNumber);

            mylist.add(objContact);



        }

        phonescursor.close();



        ContactAdapter objAdapter = new ContactAdapter(getActivity(), R.layout.activity_contacts2, mylist);

        mylistView.setAdapter(objAdapter);



        if (null != mylist && mylist.size() != 0) {

            Collections.sort(mylist, new Comparator<ContactBean>() {


                @Override

                public int compare(ContactBean lhs, ContactBean rhs) {

                    return lhs.Nameget().compareTo(rhs.Nameget());

                }

            });

               /* AlertDialog alert = new AlertDialog.Builder(Contacts.this).create();

                alert.setTitle("");



                alert.setMessage(mylist.size() + " Contact Found!!!");



                alert.setButton("OK", new DialogInterface.OnClickListener() {



                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }

                });

                alert.show();
*/


        } else {

            //   showToast("No Contact Found!!!");

        }


        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override

    public void onItemClick(AdapterView<?> listview, View v, int position,

                            long id) {

        ContactBean bean = (ContactBean) listview.getItemAtPosition(position);

        //showCallDialog(bean.getName(), bean.getPhoneNo());

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
