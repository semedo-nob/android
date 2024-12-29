package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText editTextName, editTextPassword;
    TextView textViewRegister;
    Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewRegister = findViewById(R.id.textViewRegister);
        buttonLogin = findViewById(R.id.buttonLogin);

        //db initialization
        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        // Set click listener for the login button
        buttonLogin.setOnClickListener(view -> {
            String username=editTextName.getText().toString().trim();
            String password=editTextPassword.getText().toString().trim();

            //check if the inputs are empty
            if (username.isEmpty() || password.isEmpty() ) {
                Toast.makeText(getApplicationContext(), "please enter valid credentials", Toast.LENGTH_SHORT).show();

            } else {
                //check if the credentials are valid
                if (!db.isCredentialValid(username,password)) {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }



            }
        });

        //TextView for registration redirection
        textViewRegister.setOnClickListener(view->{
                Toast.makeText(getApplicationContext(),"redirecting to registration page",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    });
    }
}


