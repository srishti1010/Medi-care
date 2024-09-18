package com.example.hospital;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class forgotPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailInput;
    private Button sendOtpButton;
    private TextView successMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Get references to views
        emailInput = findViewById(R.id.email_input);
        sendOtpButton = findViewById(R.id.send_otp_button);
        successMessage = findViewById(R.id.success_message);

        // Set up click listener for the "Send OTP" button
        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailInput.setError("Email is required");
                    return;
                }

                // Send password reset email
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Show success message
                                successMessage.setVisibility(View.VISIBLE);
                                successMessage.setText("Password reset email sent to " + email);

                                Toast.makeText(forgotPasswordActivity.this, "Check your email for further instructions", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle failure
                                Toast.makeText(forgotPasswordActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
