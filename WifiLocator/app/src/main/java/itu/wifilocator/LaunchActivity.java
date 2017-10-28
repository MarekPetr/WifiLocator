package itu.wifilocator;

/**
 * Created by petr on 10/27/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        if (!pref.contains("logged")) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        finish();
    }
}