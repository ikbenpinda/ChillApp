package com.example.etienne.chillapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.etienne.chillapp.R;
import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Login extends ActionBarActivity {

    TextView tv;
    LoginButton loginButton;
    LoginResult lResult;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/*
        // Request permission
        loginButton = (LoginButton) findViewById(R.id.);
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("public_profile");
        permissions.add("user_friends");
        permissions.add("user_events");
        permissions.add("user_interests");
        permissions.add("user_likes");
        loginButton.setReadPermissions(permissions);

        // If using in a fragment
        loginButton.setFragment(getSupportFragmentManager().findFragmentById(R.id.fb_fragment));

        FacebookSdk.sdkInitialize(this);

        // Callback registration
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, permissions);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                lResult = loginResult;
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
            */
        tv = (TextView)findViewById(R.id.login_title);
        tv.setText("Hi, " + Profile.getCurrentProfile().getFirstName() + "!");
        getUserData();
    }

    /**
     * Facebook Override for result handling after login, I guess.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void getUserData(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            tv.setText((String)object.get("first_name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
        Log.i("VIVZ", parameters.toString());
    }
}
