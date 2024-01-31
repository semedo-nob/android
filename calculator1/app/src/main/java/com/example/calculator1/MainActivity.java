package com.example.calculator1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void multiply(View v) {
        EditText a = findViewById(R.id.a);
        EditText b = findViewById(R.id.b);
        EditText sum = findViewById(R.id.sum);

        try {
            int n1 = Integer.parseInt(a.getText().toString());
            int n2 = Integer.parseInt(b.getText().toString());
            int mul = n1 * n2;
            sum.setText("value: " + mul);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            sum.setText("Invalid input");
        }
    }

    // Similarly, modify the other methods (divide, add, and subtract) following the same pattern.
    public void divide(View v) {
        EditText a = findViewById(R.id.a);
        EditText b = findViewById(R.id.b);
        EditText sum = findViewById(R.id.sum);

        try {
            int n1 = Integer.parseInt(a.getText().toString());
            int n2 = Integer.parseInt(b.getText().toString());
            int mul = n1 / n2;
            sum.setText("value: " + mul);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            sum.setText("Invalid input");
        }
    }

    // Similarly, modify the other methods (divide, add, and subtract) following the same pattern.
    public void add(View v) {
        EditText a = findViewById(R.id.a);
        EditText b = findViewById(R.id.b);
        EditText sum = findViewById(R.id.sum);

        try {
            int n1 = Integer.parseInt(a.getText().toString());
            int n2 = Integer.parseInt(b.getText().toString());
            int mul = n1 + n2;
            sum.setText("value: " + mul);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            sum.setText("Invalid input");
        }
    }

    // Similarly, modify the other methods (divide, add, and subtract) following the same pattern.
    public void subtract(View v) {
        EditText a = findViewById(R.id.a);
        EditText b = findViewById(R.id.b);
        EditText sum = findViewById(R.id.sum);

        try {
            int n1 = Integer.parseInt(a.getText().toString());
            int n2 = Integer.parseInt(b.getText().toString());
            int mul = n1 - n2;
            sum.setText("value: " + mul);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            sum.setText("Invalid input");
        }
    }

// Similarly, modify the other methods (divide, add, and subtract) following the same pattern.

}