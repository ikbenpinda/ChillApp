package com.example.etienne.chillapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.etienne.chillapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ContactsActivity extends Activity implements

        OnItemClickListener {

    Button button;

    private ListView mylistView;

    private List<ContactBean> mylist = new ArrayList<ContactBean>();



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_contacts);



        mylistView = (ListView) findViewById(R.id.listviewshow);

        mylistView.setOnItemClickListener(this);



        Cursor phonescursor = getContentResolver().query(

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



     //   ContactAdapter objAdapter = new ContactAdapter(ContactsActivity.this, R.layout.activity_contacts2, mylist);

       // mylistView.setAdapter(objAdapter);



        if (null != mylist && mylist.size() != 0) {

            Collections.sort(mylist, new Comparator<ContactBean>() {


                @Override

                public int compare(ContactBean lhs, ContactBean rhs) {

                    return lhs.Nameget().compareTo(rhs.Nameget());

                }

            });

            AlertDialog alert = new AlertDialog.Builder(

                    ContactsActivity.this).create();

            alert.setTitle("");



            alert.setMessage(mylist.size() + " Contact Found!!!");



            alert.setButton("OK", new DialogInterface.OnClickListener() {



                @Override

                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                }

            });

            alert.show();



        } else {

            showToast("No Contact Found!!!");

        }

    }



    private void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }



    @Override

    public void onItemClick(AdapterView<?> listview, View v, int position,

                            long id) {

        ContactBean bean = (ContactBean) listview.getItemAtPosition(position);

        //showCallDialog(bean.getName(), bean.getPhoneNo());

    }







    /**private void showCallDialog(String name, final String phoneNo) {

     AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this)

     .create();

     alert.setTitle("Call?");



     alert.setMessage("Are you sure want to call " + name + " ?");



     alert.setButton("No", new DialogInterface.OnClickListener() {



    @Override

    public void onClick(DialogInterface dialog, int which) {

    dialog.dismiss();

    }

    });

     alert.setButton2("Yes", new DialogInterface.OnClickListener() {



    @Override

    public void onClick(DialogInterface dialog, int which) {

    String phoneNumber = "tel:" + phoneNo;

    Intent intent = new Intent(Intent.ACTION_CALL, Uri

    .parse(phoneNumber));

    startActivity(intent);

    }

    });

     alert.show();

     }**/

}
