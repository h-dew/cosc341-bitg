package com.example.cosc341_bitg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        // Home button
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        // Hardcoded demo profiles
        List<Event> events = new ArrayList<>();
        events.add(new Event("Sat. Morning Walk", 5, "Knox Mountain, Kelowna", "April 11 - 09:00", "Join us for a nice stroll along the side of Knox Mountain! Hiking shoes are recommended, but we will be taking an easy path that should be accessible for all."));
        events.add(new Event("Book Club", 7, "Parkinson Rec Centre, Kelowna", "April 12 - 13:00", "This month's book is 'The Book Thief,' make sure to bring your copy! Snacks and drinks will be provided."));
        events.add(new Event("Garden Tour", 5, "Parkinson Rec Centre, Kelowna", "April 14 - 11:00", "Come visit the community garden behind Parkinson Rec this Tuesday! We will show you all the different vegetables and plants that are available to the public. Everyone is encouraged to bring seeds to add to the garden!"));

        EventAdapter adapter = new EventAdapter(this, events);
        ListView listView = findViewById(R.id.listViewEvents);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Event selected = events.get(position);
            Intent intent = new Intent(this, EventCardActivity.class);
            intent.putExtra("name", selected.name);
            intent.putExtra("attendees", selected.attendees);
            intent.putExtra("location", selected.location);
            intent.putExtra("date", selected.date);
            intent.putExtra("description", selected.description);
            startActivity(intent);
        });
    }
}