package com.example.cosc341_bitg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConnectedConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_confirmation);

        String name = getIntent().getStringExtra("name");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Connected!");
        }

        TextView tvMessage = findViewById(R.id.tvConfirmMessage);
        TextView tvSub = findViewById(R.id.tvConfirmSub);

        tvMessage.setText("Request sent to " + name + "!");
        tvSub.setText(name + " will be notified.");

        Button btnBack = findViewById(R.id.btnBackToMatches);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MatchFeedActivity.class);
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