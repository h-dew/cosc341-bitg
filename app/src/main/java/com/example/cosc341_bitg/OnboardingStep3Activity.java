package com.example.cosc341_bitg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingStep3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_step3);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Almost Done");
        }

        Button btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(v -> {
            Toast.makeText(this, "Account created! Please log in.", Toast.LENGTH_LONG).show();
            // Go back to login, clear the back stack so they can't go back to onboarding
            Intent intent = new Intent(this, LoginActivity.class);
            Intent home = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            boolean loggedIn = prefs.getBoolean("logged_in", false);

            if(loggedIn){
                startActivity(home);
            } else {
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}