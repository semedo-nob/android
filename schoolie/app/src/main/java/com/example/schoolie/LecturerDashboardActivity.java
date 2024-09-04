package com.example.schoolie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LecturerDashboardActivity extends AppCompatActivity {
    private static final String TAG = "LecturerDashboardActivity";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView attendanceRecordsTextView, nameTextView, emailTextView;
    private Button postNotificationButton, manageProfileButton, updateProfileButton, deleteAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_dashboard);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        attendanceRecordsTextView = findViewById(R.id.attendanceRecordsTextView);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        postNotificationButton = findViewById(R.id.postNotificationButton);
        manageProfileButton = findViewById(R.id.manageProfileButton);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        deleteAccountButton = findViewById(R.id.deleteAccountButton);

        // Load attendance records from Firestore and display in attendanceRecordsTextView
        loadAttendanceRecords();

        // Load user profile information and display in nameTextView and emailTextView
        loadProfileInfo();

        postNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNotification();
            }
        });

        manageProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LecturerDashboardActivity.this, ProfileActivity.class));
            }
        });

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });
    }

    private void loadAttendanceRecords() {
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("attendance")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        StringBuilder attendanceRecords = new StringBuilder();
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            Timestamp timestamp = document.getTimestamp("timestamp");
                            if (timestamp != null) {
                                attendanceRecords.append("Date: ").append(timestamp.toDate()).append("\n");
                            }
                        }
                        if (attendanceRecords.length() == 0) {
                            attendanceRecordsTextView.setText("No attendance records found.");
                        } else {
                            attendanceRecordsTextView.setText(attendanceRecords.toString());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LecturerDashboardActivity.this, "Error loading attendance records: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadProfileInfo() {
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users").document(userId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    String email = documentSnapshot.getString("email");
                    nameTextView.setText("Name: " + name);
                    emailTextView.setText("Email: " + email);
                } else {
                    nameTextView.setText("Name: Not found");
                    emailTextView.setText("Email: Not found");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LecturerDashboardActivity.this, "Error loading profile info: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postNotification() {
        // Implement logic to post a notification
        Toast.makeText(LecturerDashboardActivity.this, "Notification posted successfully", Toast.LENGTH_SHORT).show();
    }

    private void updateProfile() {
        // Implement logic to update the user's profile information
        Toast.makeText(LecturerDashboardActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void deleteAccount() {
        // Implement logic to delete the user's account
        Toast.makeText(LecturerDashboardActivity.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
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