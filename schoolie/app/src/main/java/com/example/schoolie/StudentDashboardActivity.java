package com.example.schoolie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class StudentDashboardActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView attendanceTextView, notificationsTextView;
    private Button markAttendanceButton, manageProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        attendanceTextView = findViewById(R.id.attendanceTextView);
        notificationsTextView = findViewById(R.id.notificationsTextView);
        markAttendanceButton = findViewById(R.id.markAttendanceButton);
        manageProfileButton = findViewById(R.id.manageProfileButton);

        loadNotifications();

        markAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markAttendance();
            }
        });

        manageProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageProfile();
            }
        });
    }

    private void loadNotifications() {
        // Load notifications from Firestore and display in notificationsTextView
    }

    private void markAttendance() {
        String userId = mAuth.getCurrentUser().getUid();
        Map<String, Object> attendance = new HashMap<>();
        attendance.put("timestamp", FieldValue.serverTimestamp());
        attendance.put("userId", userId);

        db.collection("attendance").add(attendance).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(StudentDashboardActivity.this, "Attendance marked successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StudentDashboardActivity.this, "Error marking attendance: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void manageProfile() {
        // Navigate to profile management activity
    }
}

