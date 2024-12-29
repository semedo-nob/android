package com.example.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctorActivity extends AppCompatActivity {
    TextView titleHealth;
CardView cardFDFamilyDoctor,cardFDDietician,cardFDDentist,cardFDSurgeon,cardFDCardiologist,cardFDExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);


        titleHealth=findViewById(R.id.titleHealth);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("titleHealth", "Healthcare 4 you");
        editor.apply();


        CardView exit=findViewById(R.id.cardFDExit);
        exit.setOnClickListener(view->{
            startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
        });


        CardView family=findViewById(R.id.cardFDFamilyDoctor);
        family.setOnClickListener(view -> {
            Intent it=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
            it.putExtra("title","family physician");
            startActivity(it);
        });

        CardView dietician=findViewById(R.id.cardFDDietician);
        dietician.setOnClickListener(view->{
            Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
            it.putExtra("title","Dietician");
            startActivity(it);

        });

        CardView dentist=findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(view->{
            Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
            it.putExtra("title","Dentist");
            startActivity(it);
        });

        CardView surgeon=findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(view->{
            Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
            it.putExtra("title","Surgeon");
            startActivity(it);
        });

        CardView cardiologist=findViewById(R.id.cardFDCardiologist);
        cardiologist.setOnClickListener(view->{
            Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
            it.putExtra("title","Cardiolist");
            startActivity(it);
        });



    }
}