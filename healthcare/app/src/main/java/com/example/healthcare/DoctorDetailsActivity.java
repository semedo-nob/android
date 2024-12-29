package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;


public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctordetails1 = {
            {"Doctor Name: Dr. John Heart", "Hospital Address: 101 Cardiac Ave, NY", "Experience: 15 years", "Mobile No: +1 555-1111", "Consultation Fee: 200"},
            {"Doctor Name: Dr. Susan Pulse", "Hospital Address: 202 Vascular Rd, TX", "Experience: 12 years", "Mobile No: +1 555-2222", "Consultation Fee: 180"},
            {"Doctor Name: Dr. Emily Artery", "Hospital Address: 303 Aorta Blvd, CA", "Experience: 10 years", "Mobile No: +1 555-3333", "Consultation Fee: 150"},
            {"Doctor Name: Dr. Michael Valve", "Hospital Address: 404 Vein Ln, FL", "Experience: 8 years", "Mobile No: +1 555-4444", "Consultation Fee: 130"},
            {"Doctor Name: Dr. Sarah Myocardium", "Hospital Address: 505 Atrium Ct, WA", "Experience: 20 years", "Mobile No: +1 555-5555", "Consultation Fee: 220"}
    };

    private String[][] doctordetails2 = {
            {"Doctor Name: Dr. Alan Brain", "Hospital Address: 101 Neuro Dr, NY", "Experience: 18 years", "Mobile No: +1 555-6666", "Consultation Fee: 210"},
            {"Doctor Name: Dr. Linda Cortex", "Hospital Address: 202 Synapse St, TX", "Experience: 14 years", "Mobile No: +1 555-7777", "Consultation Fee: 190"},
            {"Doctor Name: Dr. Kevin Spine", "Hospital Address: 303 Nerve Blvd, CA", "Experience: 11 years", "Mobile No: +1 555-8888", "Consultation Fee: 170"},
            {"Doctor Name: Dr. Rachel Cerebellum", "Hospital Address: 404 Neuron Ave, FL", "Experience: 9 years", "Mobile No: +1 555-9999", "Consultation Fee: 150"},
            {"Doctor Name: Dr. Ethan Lobe", "Hospital Address: 505 Axon Ct, WA", "Experience: 25 years", "Mobile No: +1 555-0000", "Consultation Fee: 230"}
    };

    private String[][] doctordetails3 = {
            {"Doctor Name: Dr. Anna Care", "Hospital Address: 101 Child St, NY", "Experience: 16 years", "Mobile No: +1 555-1234", "Consultation Fee: 100"},
            {"Doctor Name: Dr. Brian Joy", "Hospital Address: 202 Kids Blvd, TX", "Experience: 10 years", "Mobile No: +1 555-5678", "Consultation Fee: 90"},
            {"Doctor Name: Dr. Sophie Play", "Hospital Address: 303 Toddler Ln, CA", "Experience: 8 years", "Mobile No: +1 555-9101", "Consultation Fee: 110"},
            {"Doctor Name: Dr. Daniel Happy", "Hospital Address: 404 Youth Ave, FL", "Experience: 7 years", "Mobile No: +1 555-1122", "Consultation Fee: 95"},
            {"Doctor Name: Dr. Chloe Cheer", "Hospital Address: 505 Infant Ct, WA", "Experience: 20 years", "Mobile No: +1 555-3344", "Consultation Fee: 120"}
    };

    private String[][] doctordetails4 = {
            {"Doctor Name: Dr. Olivia Skin", "Hospital Address: 101 Dermis Ave, NY", "Experience: 17 years", "Mobile No: +1 555-4455", "Consultation Fee: 140"},
            {"Doctor Name: Dr. Henry Glow", "Hospital Address: 202 Epidermis Blvd, TX", "Experience: 15 years", "Mobile No: +1 555-5566", "Consultation Fee: 130"},
            {"Doctor Name: Dr. Mia Shine", "Hospital Address: 303 Pore Ln, CA", "Experience: 9 years", "Mobile No: +1 555-6677", "Consultation Fee: 120"},
            {"Doctor Name: Dr. Lucas Smooth", "Hospital Address: 404 SkinCare Ave, FL", "Experience: 11 years", "Mobile No: +1 555-7788", "Consultation Fee: 150"},
            {"Doctor Name: Dr. Emma Fresh", "Hospital Address: 505 Glow Ct, WA", "Experience: 22 years", "Mobile No: +1 555-8899", "Consultation Fee: 160"}
    };

    private String[][] doctordetails5 = {
            {"Doctor Name: Dr. Jack Bone", "Hospital Address: 101 Joint Ave, NY", "Experience: 20 years", "Mobile No: +1 555-9900", "Consultation Fee: 200"},
            {"Doctor Name: Dr. Clara Spine", "Hospital Address: 202 Skeleton Blvd, TX", "Experience: 15 years", "Mobile No: +1 555-9911", "Consultation Fee: 180"},
            {"Doctor Name: Dr. Liam Knee", "Hospital Address: 303 Hip Ln, CA", "Experience: 12 years", "Mobile No: +1 555-9922", "Consultation Fee: 160"},
            {"Doctor Name: Dr. Sophia Joint", "Hospital Address: 404 BoneCare Ave, FL", "Experience: 8 years", "Mobile No: +1 555-9933", "Consultation Fee: 140"},
            {"Doctor Name: Dr. Ethan Fracture", "Hospital Address: 505 Ortho Ct, WA", "Experience: 25 years", "Mobile No: +1 555-9944", "Consultation Fee: 220"}
    };


    SharedPreferences sharedPreferences;

    TextView textViewDDName, textViewdefault;
    Button buttonBack,Book;
    ListView listviewDD;
    HashMap<String, String> item;
    String[][] doctordetails = {};
    ArrayList list;
    SimpleAdapter adapter;
    int i;
    HashMap<String,String>selectedDoctor=null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);

        textViewDDName = findViewById(R.id.textViewDDName);
        textViewdefault = findViewById(R.id.textViewdefault);
        listviewDD = findViewById(R.id.listviewDD);
        buttonBack = findViewById(R.id.buttonBack);
        Book=findViewById(R.id.Book);



        textViewdefault.setText("title");
        String title = getIntent().getStringExtra("title");
        textViewdefault.setText(title);





        SharedPreferences sharedPreferences1=getSharedPreferences("MyPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences1.edit();
        editor.putString("titleHealth","Healthcare 4 you");
        editor.apply();


        if (title.compareTo("Family Physician") == 0)
            doctordetails = doctordetails1;
        else if (title.compareTo("Dietician") == 0)
            doctordetails = doctordetails2;
        else if (title.compareTo("Dentist") == 0)
            doctordetails = doctordetails3;
        else if (title.compareTo("Surgeon") == 0)
            doctordetails = doctordetails4;
        else
            doctordetails = doctordetails5;



        list = new ArrayList();
        for (String[] doctordetail : doctordetails) {
            item = new HashMap<>();
            item.put("line1", doctordetail[0]);
            item.put("line2", doctordetail[1]);
            item.put("line3", doctordetail[2]);
            item.put("line4", doctordetail[3]);
            item.put("line5", doctordetail[4] + "/=");
            list.add(item);
        }


        adapter = new SimpleAdapter(this, list,
                R.layout.multi_lines, new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        Book.setOnClickListener(view->{
            if(selectedDoctor==null){
                Toast.makeText(this, "Please select a doctor", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                intent.putExtra("text1",title);
                intent.putExtra("text2","Select a doctor");
                intent.putExtra("text3","Select a doctor");
                intent.putExtra("text4","Select a doctor");
                intent.putExtra("text5","Select a doctor");
                startActivity( intent);
            }else{
                Toast.makeText(this,"Please select a doctor first",Toast.LENGTH_SHORT).show();
            }


        });



        listviewDD.setAdapter(adapter);
        listviewDD.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedDoctor=(HashMap<String,String>)adapterView.getItemAtPosition(i);
            Toast.makeText(this, selectedDoctor.get("line1"), Toast.LENGTH_SHORT).show();
            Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
            it.putExtra("text1",title);
            it.putExtra("text2",doctordetails[i][0]);
            it.putExtra("text3",doctordetails[i][1]);
            it.putExtra("text4",doctordetails[i][3]);
            it.putExtra("text5",doctordetails[i][4]);
            startActivity(it);

        });



        buttonBack.setOnClickListener( view-> startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class)));
    }
}

