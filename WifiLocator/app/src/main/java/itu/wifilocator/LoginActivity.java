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

    private EditText mEmailView;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        Button mCreateAccButton = (Button) findViewById(R.id.create_acc_button);


        mCreateAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAcc();
            }
        });

        mSignInButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            attemptLogin();
        }
        });

    }

    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid surname, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid name.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
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

            Intent i= new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void createAcc() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}
