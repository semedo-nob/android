package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText FullnameEdittext, editTextAddress, ContactEditText, FeeEditText;
    TextView  textView2;
    Button buttonBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);

        buttonBack=findViewById(R.id.buttonBACK);
        FullnameEdittext = findViewById(R.id.FullnameEdittext);
        editTextAddress = findViewById(R.id.editTextAddress);
        ContactEditText = findViewById(R.id.ContactEditText);
        FeeEditText = findViewById(R.id.FeeEditText);
        textView2= findViewById(R.id.TextViewTitle);

        FullnameEdittext.setKeyListener(null);
        editTextAddress.setKeyListener(null);
        ContactEditText.setKeyListener(null);
        FeeEditText.setKeyListener(null);


        Intent it =getIntent();
        String title = it.getStringExtra("text1");
        String name = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fee = it.getStringExtra("text5");



        textView2.setText(title);
        FullnameEdittext.setText(name);
        editTextAddress.setText(address);
        ContactEditText.setText(contact);
        FeeEditText.setText("Consultation Fee:"+fee+"/=");



    }

    buttonBack.setOnClickListener(view->{
            startActivity(new Intent(BookAppointmentActivity.this,DoctorDetailsActivity.class));

};

}