package com.example.cosc341_bitg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Your Title Here");
        }

        {
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedName = prefs.getString("name", "");
            String savedAge = prefs.getString("age", "");
            String savedLocation = prefs.getString("location", "");

            EditText etName = findViewById(R.id.etName);
            EditText etAge = findViewById(R.id.etAge);
            EditText etLocation = findViewById(R.id.etLocation);

            etName.setText(savedName);
            etAge.setText(savedAge);
            etLocation.setText(savedLocation);
        }

        Button btnNext = findViewById(R.id.btnNext1);
        btnNext.setOnClickListener(v -> {
            EditText etName = findViewById(R.id.etName);
            EditText etAge = findViewById(R.id.etAge);
            EditText etLocation = findViewById(R.id.etLocation);

            String name = etName.getText().toString().trim();
            String age = etAge.getText().toString().trim();
            String location = etLocation.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save to SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefs.edit()
                    .putString("name", name)
                    .putString("age", age)
                    .putString("location", location)
                    .apply();

            Intent intent = new Intent(this, OnboardingStep2Activity.class);
            startActivity(intent);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}