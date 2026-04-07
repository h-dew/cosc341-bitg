package com.example.cosc341_bitg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class MatchAdapter extends ArrayAdapter<Match> {

    public MatchAdapter(Context context, List<Match> matches) {
        super(context, 0, matches);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_match, parent, false);
        }

        Match match = getItem(position);

        TextView tvName = convertView.findViewById(R.id.tvMatchName);
        TextView tvInterests = convertView.findViewById(R.id.tvMatchInterests);
        TextView tvLocation = convertView.findViewById(R.id.tvMatchLocation);

        tvName.setText(match.name + ", " + match.age);
        tvInterests.setText("Interests: " + match.interests);
        tvLocation.setText("📍 " + match.location);

        return convertView;
    }
}