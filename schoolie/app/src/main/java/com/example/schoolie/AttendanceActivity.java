package com.example.schoolie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {
    private static final String TAG = "AttendanceActivity";
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private AttendanceAdapter attendanceAdapter;
    private List<Attendance> attendanceList;
    private Spinner filterSpinner;
    private Button sortButton;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // Initialize Firebase Auth and Firestore
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        attendanceList = new ArrayList<>();

        // Set up RecyclerView
        RecyclerView attendanceRecyclerView = findViewById(R.id.attendanceRecyclerView);
        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceAdapter = new AttendanceAdapter(attendanceList, this);
        attendanceRecyclerView.setAdapter(attendanceAdapter);

        // Initialize UI components
        filterSpinner = findViewById(R.id.filterSpinner);
        sortButton = findViewById(R.id.sortButton);
        calendarView = findViewById(R.id.calendarView);

        // Set up filter spinner
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this,
                R.array.attendance_status_options, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedStatus = (String) parent.getItemAtPosition(position);
                attendanceAdapter.filter(selectedStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                attendanceAdapter.filter("All");
            }
        });

        // Set up sort button
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendanceAdapter.sort();
            }
        });

        // Set up calendar view
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
            filterAttendanceByDate(selectedDate);
        });

        // Load attendance data
        loadAttendance();
    }

    private void loadAttendance() {
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("attendance")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            attendanceList.clear(); // Clear previous data
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String date = document.getString("date");
                                String status = document.getString("status"); // Assuming you have a status field
                                attendanceList.add(new Attendance(date, status));
                            }
                            attendanceAdapter.notifyDataSetChanged(); // Notify adapter of data changes
                        } else {
                            handleLoadAttendanceError(task.getException());
                        }
                    }
                });
    }

    private void handleLoadAttendanceError(Exception exception) {
        Toast.makeText(AttendanceActivity.this, "Failed to load attendance", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Failed to load attendance", exception);
    }

    private void filterAttendanceByDate(String date) {
        List<Attendance> filteredList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            if (attendance.getDate().equals(date)) {
                filteredList.add(attendance);
            }
        }
        attendanceAdapter.filter(filteredList.toString());
        attendanceAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        loadAttendance(); // Reload attendance data when activity resumes
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }
}