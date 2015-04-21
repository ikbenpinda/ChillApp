package com.example.etienne.chillapp.utilities;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Etienne on 21-4-2015.
 */
public class UserPreferencesEditorHelper extends Activity{

    public void setInitialUse(){
        SharedPreferences preferences = getPreferences(0);
        preferences.getBoolean("FirstUse", true);
    }

    /**
     * Exposes SharedPreferences to non-activities.
     * @return
     */
    public SharedPreferences getPreferences(){
        return getPreferences(0);
    }

    /**
     *
     * @param preference
     * @param value
     */
    public void editValue(String preference, boolean value){
        SharedPreferences preferences = getPreferences(0);

        // Edit here.
        preferences.edit().putBoolean(preference, value).commit();
    }
}
