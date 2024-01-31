package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    EditText temperature;
    RadioButton celcius;
    RadioButton farhrenheit;
    Button convert;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperature = findViewById(R.id.input);
        celcius = findViewById(R.id.celcius);
        farhrenheit = findViewById(R.id.farhrenheit);
        convert = findViewById(R.id.convert);
        result = findViewById(R.id.result);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double temp =
                        Double.parseDouble(temperature.getText().toString());
                if (celcius.isChecked()) {
                    double f = temp * 9 / 5 + 32;
                    result.setText(String.valueOf(f));
                } else if (farhrenheit.isChecked()) {
                    double c = (temp - 32) * 5 / 9;
                    result.setText(String.valueOf(c));
                }
            }
        });
    }
}