package com.jakefallin.rhsapp;

/**
 * Created by Jake on 5/2/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import mehdi.sakout.fancybuttons.FancyButton;
import pub.devrel.easygoogle.Google;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.credentials.Credential;

import pub.devrel.easygoogle.gac.SignIn;

/**
  * Unused. Provides Implementation for google sign-in. May be of use in the future.
  */

public class SignInActivity extends AppCompatActivity implements SignIn.SignInListener, View.OnClickListener {

    private Google mGoogle;

    public static String TAG = "sample.MainActivity";

    // SmartLock data/fields
    private Credential mCredential;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private FancyButton skipButton, continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        mGoogle = new Google.Builder(this)
                .enableSignIn(this)
                .build();

        mGoogle.getSignIn().createSignInButton(this, (ViewGroup) findViewById(R.id.layout_sign_in));

        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.skip_button).setOnClickListener(this);
        findViewById(R.id.continue_button).setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Subscribe to the "easygoogle" topic
        mGoogle.getMessaging().subscribeTo("/topics/easygoogle");
    }

    @Override
    public void onSignedIn(GoogleSignInAccount account) {
        Log.d(TAG, "onSignedIn:" + account.getEmail());
        ((TextView) findViewById(R.id.sign_in_status)).setText(
                getString(R.string.status_signed_in_fmt, account.getDisplayName(), account.getEmail()));

    }

    @Override
    public void onSignedOut() {
        ((TextView) findViewById(R.id.sign_in_status)).setText(R.string.status_signed_out);
    }

    @Override
    public void onSignInFailed() {
        ((TextView) findViewById(R.id.sign_in_status)).setText(R.string.status_sign_in_failed);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_out_button:
                // Sign out with Google
                mGoogle.getSignIn().signOut();
                break;
            case R.id.skip_button:
                Intent i1 = new Intent(SignInActivity.this, StartupActivity.class);
                startActivity(i1);
            case R.id.continue_button:
                Intent i2 = new Intent(SignInActivity.this, StartupActivity.class);
                startActivity(i2);
        }
    }


}
