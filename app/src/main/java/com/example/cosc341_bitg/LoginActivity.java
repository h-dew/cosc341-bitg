package com.example.cosc341_bitg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Your Title Here");
        }

        Button btnLogin = findViewById(R.id.btnDoLogin);
        TextView tvError = findViewById(R.id.tvLoginError);

        btnLogin.setOnClickListener(v -> {
            EditText etName = findViewById(R.id.etLoginName);
            EditText etPassword = findViewById(R.id.etLoginPassword);

            String name = etName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedName = prefs.getString("name", "");
            String savedPassword = prefs.getString("password", "");

            if (name.equals(savedName) && password.equals(savedPassword)) {
                SharedPreferences.Editor editor = getSharedPreferences("UserPrefs", MODE_PRIVATE).edit();
                editor.putBoolean("logged_in", true);
                editor.apply();

                // Login success — go to main app (for now back to step 2)
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                tvError.setText("Incorrect name or password.");
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}