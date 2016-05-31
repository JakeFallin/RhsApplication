package com.jakefallin.rhsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.jakefallin.rhsapp.Util.AppController;

/**
 * Created by Jake on 4/26/2016.
 */
public class SplashScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        boolean x = true;SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        boolean firstTime = sharedPrefs.getBoolean("first", true);

        if (firstTime) {

            Intent intent = new Intent(SplashScreen.this, StartupActivity2.class);
            startActivity(intent);
            editor.putBoolean("first", false);
            editor.apply();
            finish();
        } else {

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
