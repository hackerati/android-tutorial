package com.thehackerati.custombaseadapter;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.thehackerati.custombaseadapter.adapters.MoonsAdapter;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    private List<String> moons;
    private ListView moonsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moonsListView = (ListView) findViewById(R.id.list_view);

        Resources res = getResources();
        String[] moonsArray = res.getStringArray(R.array.moons_array);
        moons = Arrays.asList(moonsArray);
    }


    @Override
    protected void onResume() {
        super.onResume();

        MoonsAdapter moonsAdapter = new MoonsAdapter(moons);
        moonsListView.setAdapter(moonsAdapter);
    }
}
