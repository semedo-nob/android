package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest");

        if (username != null && !username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Welcome " + username, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Welcome Guest", Toast.LENGTH_SHORT).show();
        }


        CardView exit=findViewById(R.id.CARDExit);
        exit.setOnClickListener(view -> {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));

        });

        CardView FindDoctor=findViewById(R.id.cardFinddoctor);
        FindDoctor.setOnClickListener(view-> startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class)));
    }
}