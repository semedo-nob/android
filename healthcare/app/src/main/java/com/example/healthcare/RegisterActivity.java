package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText UsernameEditText,editTextEmail,PasswordEditText,ConfirmPasswordEditText2;
    Button buttonRegister;
    TextView textViewLoginAccount;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        UsernameEditText=findViewById(R.id.FullnameEdittext);
        editTextEmail=findViewById(R.id.editTextAddress);
        PasswordEditText=findViewById(R.id.PasswordEditText);
        ConfirmPasswordEditText2=findViewById(R.id.FeeEditText);


        buttonRegister=findViewById(R.id.buttonRegister);
        textViewLoginAccount=findViewById(R.id.textViewLoginAccount);

        //Database initialization
        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        buttonRegister.setOnClickListener(view -> {
            String username=UsernameEditText.getText().toString().trim();
            String email=editTextEmail.getText().toString().trim();
            String password=PasswordEditText.getText().toString().trim();
            String confirmPassword=ConfirmPasswordEditText2.getText().toString().trim();

            if(username.isEmpty()||email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()){
                Toast.makeText(getApplicationContext(),"please fill in the required fields",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!ValidationUtils.isValidEmail(email)){
                Toast.makeText(getApplicationContext(),"email is invalid",Toast.LENGTH_SHORT).show();
                return;
            }
            if (db.isEmailTaken(email)){
                Toast.makeText(getApplicationContext(),"email alredy in use",Toast.LENGTH_SHORT).show();
                return;
            }


            if(!password.equals(confirmPassword)){
                Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!ValidationUtils.isValidPassword(password)){
                Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters, a letter, a digit, and a special character", Toast.LENGTH_SHORT).show();
                return;
            }
            db.register(username,email,password);
            Toast.makeText(getApplicationContext(),"Registration succesfull",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            finish();
        });


        textViewLoginAccount.setOnClickListener(view1 -> {
            Toast.makeText(getApplicationContext(),"Redirecting..",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });




    }
        }


















