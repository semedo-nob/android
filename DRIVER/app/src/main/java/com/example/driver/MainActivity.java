package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatePicker datePicker = findViewById(R.id.datePicker);
        Button checkButton = findViewById(R.id.checkButtonPSV);
        Button checkButtonPrivate = findViewById(R.id.checkButtonPrivate);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = datePicker.getYear();
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                int age = currentYear - selectedYear;

                if (age > 24 && age < 60) {
                    Toast.makeText(MainActivity.this, "You are eligible for the Public Service Vehicle (PSV) permit.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You are not eligible for the Public Service Vehicle (PSV) permit.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkButtonPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = datePicker.getYear();
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                int age = currentYear - selectedYear;

                if (age >= 18) {
                    Toast.makeText(MainActivity.this, "You are eligible for the private car permit.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You are not eligible for the private car permit.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

