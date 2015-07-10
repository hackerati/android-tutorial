package com.thehackerati.worlds.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.thehackerati.worlds.MainActivity;
import com.thehackerati.worlds.R;

public class SecondWorld extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String response = "You are puny earthlings";

        Intent responseIntent = new Intent();
        responseIntent.putExtra(MainActivity.KEY_HELLO, response);

        setResult(RESULT_OK, responseIntent);

        finish();

    }
}
