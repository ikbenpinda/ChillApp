package com.hang.devicetodevice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

public class MainActivity extends Activity {

    ShareExternalServer appUtil;
    String regId;
    String userName;
    AsyncTask<Void, Void, String> shareRegidTask;
    //private static final String TAG = MainActivity.class.getName();

    public static final int PICK_CONTACT    = 1;

    Button                  btnContacts;

    TextView                txtContacts1;

    TextView                txtContacts2;
    EditText toUser;
    EditText message_Activity;
    EditText message_Place;
    TextView txtPending;
    Button btnSendMessage;
    DatePicker datePick;
    TimePicker timePick;
    String path;

    /*public void checkData() throws IOException {
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File invReceivedStat = new File("/sdcard/invSentStat.txt");
        File invSentStat = new File(path + "invSentStat.txt");
        File invAccepted = new File("invAccepted.txt");

        if (invSentStat.exists()) {
        }
        if (invReceivedStat.exists()) {
        }
        if (invAccepted.exists()) {
        }


    }

    public void readFromJSON(String filename, String user_name) throws IOException, ParseException, JSONException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path + "/" + filename + ".json"));
        //SONObject jsonObject = (JSONObject) obj;
        JSONArray array = new JSONArray();
        array.add(obj);
        String username = "";
        String activity = "";
        String location = "";
        String date = "";
        String time = "";
        Iterator<String> iterator = array.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i == 0) {
                activity = iterator.next();
            } else if (i == 1) {
                location = iterator.next();
            } else if (i == 2) {
                date = iterator.next();
            } else if (i == 3) {
                time = iterator.next();
            }
            i++;
        }
        txtPending = (TextView) findViewById(R.id.textPending);
        String string = "Your invite to: "+ user_name +" to do: " + activity + " \n" +" at: "+location+ " on:"+date+ " at: "+time+" is still pending";
        string = string.replace("\\\n", System.getProperty("line.separator"));
        txtPending.setLines(10);
        txtPending.setText(string);
        }

    public void arrayIterator(JSONArray array)
    {
        String username = "";
        String activity = "";
        String location = "";
        Iterator<String> iterator = array.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i == 0) {
                activity = iterator.next();
            } else if (i == 1) {
                location = iterator.next();
            }
        }

    }


    public void writeToJSON(String filename, String username, String activity, String place, String date, String time) throws JSONException, IOException {
        JSONObject obj;
        JSONArray array = new JSONArray();
        // Writing to a file
        File file = new File(path, filename + ".json");
        if (!file.exists()) {
            file.createNewFile();
            obj = new JSONObject();
        } else {
            JSONParser parser = new JSONParser();
            Object object = null;
            try {
                object = parser.parse(new FileReader(path + "/" + filename + ".json"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj = (JSONObject) object;
        }

        array.add(activity);
        array.add(place);
        array.add(date);
        array.add(time);

        obj.put(username, array);
        FileWriter fileWriter = new FileWriter(file);
        System.out.println("Writing JSON object to file");
        System.out.println("-----------------------");
        System.out.print(obj);

        fileWriter.write(obj.toString());
        fileWriter.flush();
        fileWriter.close();

    }
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appUtil = new ShareExternalServer();

        regId = getIntent().getStringExtra("regId");
        Log.d("MainActivity", "regId: " + regId);

        userName = getIntent().getStringExtra(Config.REGISTER_NAME);
        Log.d("MainActivity", "userName: " + userName);

        shareRegidTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String result = appUtil
                        .shareRegIdWithAppServer(regId, userName);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                shareRegidTask = null;
                Toast.makeText(getApplicationContext(), result,
                        Toast.LENGTH_LONG).show();
            }

        };
        btnContacts = (Button) findViewById(R.id.btn_contacts);

        txtContacts1 = (TextView) findViewById(R.id.txt_contacts_name);

        txtContacts2 = (TextView) findViewById(R.id.txt_contacts_number);

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);

            }

        });

        // to send message_Activity to another device via Google GCM
        btnSendMessage = (Button) findViewById(R.id.sendMessage);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                toUser = (EditText) findViewById(R.id.toUser);
                String toUserName = toUser.getText().toString();

                message_Activity = (EditText) findViewById(R.id.messageActivity);
                String messageActivity = message_Activity.getText().toString();

                message_Place = (EditText) findViewById(R.id.messagePlace);
                String messagePlace = message_Place.getText().toString();

                datePick = (DatePicker)findViewById(R.id.datePicker);
                String date = String.valueOf(datePick.getDayOfMonth()) +"-"
                        + String.valueOf(datePick.getMonth()) + "-"
                        + String.valueOf(datePick.getYear());

                timePick = (TimePicker)findViewById(R.id.timePicker);
                String time = String.valueOf(timePick.getCurrentHour()) +":"
                        + String.valueOf(timePick.getCurrentMinute());

                if (TextUtils.isEmpty(toUserName)) {
                    Toast.makeText(getApplicationContext(),
                            "To User is empty!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(messageActivity)) {
                    Toast.makeText(getApplicationContext(),
                            "Activity is empty!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(messagePlace)) {
                    Toast.makeText(getApplicationContext(),
                            "Place is empty!", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("MainActivity", "Sending message_Activity to user: "
                            + toUserName);
                    sendMessageToGCMAppServer(toUserName, messageActivity, messagePlace, date, time);
                    //data for txt files
                    String data = toUserName + "," + messageActivity + "," + messagePlace + "'";
                    // writeToFile2("invSentStat", data);
                  /*  try {
                        writeToJSON("invSentStat", toUserName, messageActivity, messagePlace, date, time);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        readFromJSON("invSentStat", toUserName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                  */
                    txtPending = (TextView) findViewById(R.id.textPending);
                    txtPending.setText(data);

                    //try {
                    //   checkData();
                    ///} catch (IOException e) {
                    //    e.printStackTrace();
                    // }
                }
            }
        });

        shareRegidTask.execute(null, null, null);
    }

    private void sendMessageToGCMAppServer(final String toUserName,
                                           final String messageActivity, final String messagePlace,
                                           final String date, final String time) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                String result = appUtil.sendMessage(userName, toUserName,
                        messageActivity, messagePlace, date, time);
                Log.d("MainActivity", "Result: " + result);
                return result;
            }

            @Override
            protected void onPostExecute(String msg) {
                Log.d("MainActivity", "Result: " + msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
                        .show();
            }
        }.execute(null, null, null);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {

        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {

            case (PICK_CONTACT):

                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();

                    Cursor c = getContentResolver().query(contactData, null, null, null, null);

                    if (c.moveToFirst()) {

                        String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));//+" : c.getInt(c.getColumnIndexOrThrow(People.NUMBER));

                        //

                        txtContacts1.setText(name);
                        TextView to_User = (TextView)findViewById(R.id.toUser);
                        TextView to_Activity = (TextView)findViewById(R.id.messageActivity);
                        TextView to_Place = (TextView)findViewById(R.id.messagePlace);
                        to_User.setText(name);
                        to_Activity.setText("Bowlen");
                        to_Place.setText("Eindhoven");

                    }

                }

                break;

        }

    }
}
