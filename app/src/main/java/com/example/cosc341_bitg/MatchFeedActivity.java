package com.example.cosc341_bitg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MatchFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_feed);

        // Home button
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        // Hardcoded demo profiles
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Margaret", 68, "Kelowna, BC", "Gardening, Reading", "I love spending time in my garden and reading historical novels."));
        matches.add(new Match("Carol", 71, "Vernon, BC", "Music, Walking", "Retired music teacher who enjoys morning walks by the lake."));
        matches.add(new Match("Susan", 64, "Penticton, BC", "Cooking, Travel", "I love trying new recipes and have travelled to over 20 countries!"));
        matches.add(new Match("Barbara", 67, "Kelowna, BC", "Reading, Gardening", "Book club organizer and passionate about growing her own vegetables."));
        matches.add(new Match("James", 70, "Kelowna, BC", "Walking, Travel", "Retired teacher who enjoys hiking the local trails."));

        MatchAdapter adapter = new MatchAdapter(this, matches);
        ListView listView = findViewById(R.id.listViewMatches);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Match selected = matches.get(position);
            Intent intent = new Intent(this, ProfileCardActivity.class);
            intent.putExtra("name", selected.name);
            intent.putExtra("age", selected.age);
            intent.putExtra("location", selected.location);
            intent.putExtra("interests", selected.interests);
            intent.putExtra("bio", selected.bio);
            startActivity(intent);
        });
    }
}