package com.example.cosc341_bitg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, List<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_event, parent, false);
        }

        Event event = getItem(position);

        TextView tvName = convertView.findViewById(R.id.tvMatchName);
        TextView tvDate = convertView.findViewById(R.id.tvMatchDate);
        TextView tvLocation = convertView.findViewById(R.id.tvMatchLocation);

        tvName.setText(event.name);
        tvDate.setText("🗓️ " + event.date);
        tvLocation.setText("📍 " + event.location);

        return convertView;
    }
}