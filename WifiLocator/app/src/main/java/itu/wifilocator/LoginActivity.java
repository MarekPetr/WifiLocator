package itu.wifilocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


/**
 * Created by petr on 10/27/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText mNameView;
    private EditText mSurNameView;
    private EditText mNickView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNameView = (EditText) findViewById(R.id.name);
        mSurNameView = (EditText) findViewById(R.id.surname);
        mNickView = (EditText) findViewById(R.id.nick);

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            attemptLogin();
        }
        });

    }

    private void attemptLogin() {


        // Reset errors.
        mNameView.setError(null);
        mSurNameView.setError(null);
        mNickView.setError(null);

        // Store values at the time of the login attempt.
        String name = mNameView.getText().toString();
        String surName = mSurNameView.getText().toString();
        String nick = mNickView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid surname, if the user entered one.
        if (TextUtils.isEmpty(surName)) {
            mSurNameView.setError(getString(R.string.error_field_required));
            focusView = mSurNameView;
            cancel = true;
        }

        // Check for a valid name.
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("logged", true);
            editor.apply();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish(); // close login activity
        }
    }
}
