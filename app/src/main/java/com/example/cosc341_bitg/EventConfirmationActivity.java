package com.example.cosc341_bitg;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_confirmation);

        String name = getIntent().getStringExtra("name");
        String location = getIntent().getStringExtra("location");
        String date = getIntent().getStringExtra("date");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Connected!");
        }

        TextView tvSub = findViewById(R.id.tvConfirmSub);
        TextView tvSub2 = findViewById(R.id.tvConfirmSub2);
        TextView tvSub3 = findViewById(R.id.tvConfirmSub3);

        tvSub.setText(name);
        tvSub2.setText(location);
        tvSub3.setText(date);

        Button btnBack = findViewById(R.id.btnBackToEvents);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, EventsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}