package com.example.cosc341_bitg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingStep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_step1);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Your Title Here");
        }

        Button btnNext = findViewById(R.id.btnNext1);
        btnNext.setOnClickListener(v -> {
            EditText etName = findViewById(R.id.etName);
            EditText etAge = findViewById(R.id.etAge);
            EditText etLocation = findViewById(R.id.etLocation);
            EditText etPassword = findViewById(R.id.etPassword);

            String name = etName.getText().toString().trim();
            String age = etAge.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Name and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save to SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefs.edit()
                    .putString("name", name)
                    .putString("age", age)
                    .putString("location", location)
                    .putString("password", password)
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