package com.example.cosc341_bitg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_card);

        String name = getIntent().getStringExtra("name");
        int attendees = getIntent().getIntExtra("attendees", 0);
        String location = getIntent().getStringExtra("location");
        String date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name);
        }

        TextView tvName = findViewById(R.id.tvEventName);
        TextView tvLocation = findViewById(R.id.tvEventLocation);
        TextView tvDate = findViewById(R.id.tvEventDate);
        TextView tvAttendees = findViewById(R.id.tvEventAttendees);
        TextView tvDescription = findViewById(R.id.tvEventDescription);

        tvName.setText(name);
        tvLocation.setText("📍 " + location);
        tvDate.setText("🗓️ " + date);
        tvAttendees.setText("👥 " + Integer.toString(attendees) + " joined");
        tvDescription.setText(description);

        Button btnRSVP = findViewById(R.id.btnRSVP);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish()); // goes back to event feed

        TextView tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        tvToolbarTitle.setText(name);

        btnRSVP.setOnClickListener(v -> {
            Intent intent = new Intent(this, EventConfirmationActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("location", location);
            intent.putExtra("date", date);
            startActivity(intent);
        });
    }
}