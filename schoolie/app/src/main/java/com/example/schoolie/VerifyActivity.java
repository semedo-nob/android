package com.example.schoolie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerifyActivity extends AppCompatActivity {

    private static final String TAG = "VerifyActivity";
    private EditText emailEditText, passwordEditText;
    private Button verifyButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        initializeUI();

        // Set click listener for the verify button
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyUser();
            }
        });
    }

    private void initializeUI() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        verifyButton = findViewById(R.id.verifyButton);
    }

    private void verifyUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate inputs
        if (!isEmailValid(email) || !isPasswordValid(password)) return;

        Log.d(TAG, "Attempting to sign in user...");

        // Sign in user with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser != null) {
                        Log.d(TAG, "User signed in successfully.");
                        checkUserRole(currentUser.getUid());
                    } else {
                        handleLoginError(new Exception("User is null after login."));
                    }
                } else {
                    handleLoginError(task.getException());
                }
            }
        });
    }

    private boolean isEmailValid(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Invalid Email");
            emailEditText.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            showToast("Password must be at least 6 characters");
            passwordEditText.requestFocus();
            return false;
        }
        return true;
    }

    private void handleLoginError(Exception exception) {
        showToast("Verification Failed: " + exception.getMessage());
        Log.e(TAG, "Verification Failed", exception);
    }

    private void checkUserRole(String userId) {
        Log.d(TAG, "Checking user role...");
        db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    String role = task.getResult().getString("role");
                    Log.d(TAG, "Role retrieved: " + role);
                    handleUserRole(role);
                } else {
                    handleRoleRetrievalError(task.getException());
                }
            }
        });
    }

    private void handleUserRole(String role) {
        if (role != null) {
            Log.d(TAG, "User role: " + role);
            Intent intent;
            switch (role) {
                case "Lecturer":
                    intent = new Intent(VerifyActivity.this, LecturerDashboardActivity.class);
                    break;
                case "Student":
                    intent = new Intent(VerifyActivity.this, StudentDashboardActivity.class);
                    break;
                default:
                    showToast("No valid role found for the user.");
                    return;
            }
            startActivity(intent);
            finish();
        } else {
            showToast("Role is null.");
        }
    }

    private void handleRoleRetrievalError(Exception exception) {
        showToast("Error retrieving user role: " + exception.getMessage());
        Log.e(TAG, "Error retrieving user role", exception);
    }

    private void showToast(String message) {
        Toast.makeText(VerifyActivity.this, message, Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }
}
