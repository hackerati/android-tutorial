package com.thehackerati.worlds;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thehackerati.worlds.activities.SecondWorld;


public class MainActivity extends ActionBarActivity {


    public static final String KEY_HELLO = "key_hello";
    public static final int REQUEST_COMS = 99;

    private Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button) findViewById(R.id.my_button);

        myButton.setText("blah");
    }

    @Override
    protected void onResume() {
        super.onResume();

        setupListeners();
    }

    private void setupListeners() {
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWorld();
            }
        });

    }

    private void launchWorld() {
        Intent launchComIntent = new Intent(this, SecondWorld.class);
        startActivityForResult(launchComIntent, REQUEST_COMS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_COMS) {
            if (resultCode == RESULT_OK) {
                String responseMsg = data.getStringExtra(KEY_HELLO);

                Toast.makeText(this, responseMsg, Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
