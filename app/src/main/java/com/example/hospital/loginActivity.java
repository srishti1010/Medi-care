package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView forgotPasswordLink;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Auth

        // Get references to the views
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        forgotPasswordLink = findViewById(R.id.forgot_password);
        createAccountButton = findViewById(R.id.create_account_button);

        // Set up click listener for login button
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Validate inputs
            if (TextUtils.isEmpty(email)) {
                emailInput.setError("Email is required");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                passwordInput.setError("Password is required");
                return;
            }

            // Sign in with Firebase Auth
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign-in success, navigate to home activity
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginActivity.this, home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginActivity.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Set up click listener for "Forgot Password?"
        forgotPasswordLink.setOnClickListener(v -> {
            // Navigate to Forgot Password screen
            Intent intent = new Intent(loginActivity.this, forgotPasswordActivity.class);
            startActivity(intent);
        });

        // Set up click listener for "Create Account" button
        createAccountButton.setOnClickListener(v -> {
            // Navigate to Create Account screen
            Intent intent = new Intent(loginActivity.this, createAccount.class);
            startActivity(intent);
        });
    }
}
