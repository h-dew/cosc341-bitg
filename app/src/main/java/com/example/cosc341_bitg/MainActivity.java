package com.example.cosc341_bitg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnLogIn = findViewById(R.id.btnLogIn);

        SharedPreferences.Editor editor = getSharedPreferences("UserPrefs", MODE_PRIVATE).edit();
        editor.putBoolean("logged_in", false);
        editor.apply();

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, OnboardingStep1Activity.class);
            startActivity(intent);
        });

        btnLogIn.setOnClickListener(v -> {
            // For now, also go to step 1 — hook up later
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}