package com.thehackerati.custombaseadapter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thehackerati.custombaseadapter.R;

import java.util.List;

public class MoonsAdapter extends BaseAdapter {

    private final List<String> moons;

    public MoonsAdapter(List<String> moons) {
        this.moons = moons;
    }

    @Override
    public int getCount() {
        return moons.size();
    }

    @Override
    public Object getItem(int position) {
        return moons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            //if the view was null it wasn't recycled
            // we need to inflate a new one
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_row, parent, false);
        }



        TextView moonTextView = (TextView) convertView.findViewById(R.id.text);
        String moonName = moons.get(position);
        moonTextView.setText(moonName);

        return convertView;
    }
}
