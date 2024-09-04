package com.example.schoolie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d(TAG, "User is signed in: " + currentUser.getUid());
            checkCustomClaims(currentUser);
        } else {
            Log.d(TAG, "No user is signed in. Redirecting to RegisterActivity.");
            redirectToRegister();
        }
    }

    private void checkCustomClaims(FirebaseUser user) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if (task.isSuccessful()) {
                    handleUserRole(task.getResult().getClaims().get("role"));
                } else {
                    Log.e(TAG, "Failed to get user claims: " + task.getException().getMessage());
                    showErrorToast("Failed to get user claims: " + task.getException().getMessage());
                }
            }
        });
    }

    private void handleUserRole(Object roleObject) {
        if (roleObject instanceof String) {
            String role = (String) roleObject;
            switch (role) {
                case "Lecturer":
                    Log.d(TAG, "User is a Lecturer. Redirecting to LecturerDashboardActivity.");
                    startActivity(new Intent(MainActivity.this, LecturerDashboardActivity.class));
                    break;
                case "Student":
                    Log.d(TAG, "User is a Student. Redirecting to StudentDashboardActivity.");
                    startActivity(new Intent(MainActivity.this, StudentDashboardActivity.class));
                    break;
                default:
                    Log.e(TAG, "Error retrieving user role.");
                    showErrorToast("Error retrieving user role.");
            }
            finish();
        } else {
            Log.e(TAG, "Role is not a valid string.");
            showErrorToast("Error retrieving user role.");
        }
    }

    private void redirectToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    private void showErrorToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
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
