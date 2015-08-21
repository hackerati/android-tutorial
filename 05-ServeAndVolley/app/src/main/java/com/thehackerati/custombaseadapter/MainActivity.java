package com.thehackerati.custombaseadapter;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thehackerati.custombaseadapter.adapters.MoonsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity {

    private List<String> moons;
    private ListView moonsListView;
    private TextView textView;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViews();

        loadMoonsArray();

        initRequestQueue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelVolley();
    }

    /**
     * This is one way to cancel all requests in the queue.
     * However it is generally recommended that requests are cancelled individually.
     */
    private void cancelVolley() {
        queue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    private void initRequestQueue() {
        queue = Volley.newRequestQueue(this);
    }

    private void findViews() {
        moonsListView = (ListView) findViewById(R.id.list_view);
        textView = (TextView) findViewById(R.id.text);
    }

    private void loadMoonsArray() {
        Resources res = getResources();
        String[] moonsArray = res.getStringArray(R.array.moons_array);
        moons = Arrays.asList(moonsArray);
    }


    @Override
    protected void onResume() {
        super.onResume();

        MoonsAdapter moonsAdapter = new MoonsAdapter(moons);
        moonsListView.setAdapter(moonsAdapter);

        loadWithVolley();
    }

    private void loadWithVolley() {

        String url = "https://en.wikipedia.org/w/api.php?action=query&generator=random&grnnamespace=0&prop=extracts&exchars=500&format=json";

        // Request a string response from the provided URL.
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                url,
                responseListener,
                errorListener);

        // Add the request to the RequestQueue.
        queue.add(req);
    }

    private Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            String responseString = getStringFromJSON(response);
            Spanned sp = Html.fromHtml(responseString); //Spanned is just stylized text
            textView.setText(sp);
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            textView.setText("VolleyError" + error);
        }
    };


    /**
     * Simple custom parser for the wikipedia data.
     * In the real world, you would use a library to parse JSON into Plain Old Java Objects (POJOs)
     */
    private String getStringFromJSON(JSONObject jsonObject) {

        String extract = "";

        try {
            JSONObject query = jsonObject.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");
            Iterator keys = pages.keys();
            while(keys.hasNext()) {
                String pageId = (String)keys.next();
                JSONObject pageObject = pages.getJSONObject(pageId);

                extract = pageObject.getString("extract");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return extract;
    }
}
