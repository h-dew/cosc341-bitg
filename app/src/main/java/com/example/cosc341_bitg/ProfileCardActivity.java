package com.example.cosc341_bitg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_card);

        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);
        String location = getIntent().getStringExtra("location");
        String interests = getIntent().getStringExtra("interests");
        String bio = getIntent().getStringExtra("bio");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name + "'s Profile");
        }

        TextView tvName = findViewById(R.id.tvProfileName);
        TextView tvLocation = findViewById(R.id.tvProfileLocation);
        TextView tvInterests = findViewById(R.id.tvProfileInterests);
        TextView tvBio = findViewById(R.id.tvProfileBio);

        tvName.setText(name + ", " + age);
        tvLocation.setText("📍 " + location);
        tvInterests.setText("Interests: " + interests);
        tvBio.setText(bio);

        Button btnConnect = findViewById(R.id.btnConnect);
        Button btnPass = findViewById(R.id.btnPass);
        Button btnReport = findViewById(R.id.btnReport);
        Button btnBlock = findViewById(R.id.btnBlock);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish()); // goes back to match feed

        TextView tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        tvToolbarTitle.setText(name + "'s Profile");

        btnConnect.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConnectedConfirmationActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        });

        btnPass.setOnClickListener(v -> {
            Toast.makeText(this, "Passed on " + name, Toast.LENGTH_SHORT).show();
            finish(); // go back to match feed
        });

        btnReport.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReportActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        });

        btnBlock.setOnClickListener(v -> {
            Toast.makeText(this, name + " has been blocked.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}