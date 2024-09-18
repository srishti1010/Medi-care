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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createAccount extends AppCompatActivity {

    private EditText nameInput, emailInput, passwordInput, confirmPasswordInput;
    private Button loginButton;
    private TextView alreadyHaveAccount;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account); // Make sure this matches the layout XML file name

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initializing the views with IDs from XML
        nameInput = findViewById(R.id.name_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        confirmPasswordInput = findViewById(R.id.confirm_password_input);
        loginButton = findViewById(R.id.login_button);
        alreadyHaveAccount = findViewById(R.id.already_have_account);

        // Set OnClickListener on login button
        loginButton.setOnClickListener(v -> handleRegister());

        // Set OnClickListener on "Already have an account?" text
        alreadyHaveAccount.setOnClickListener(v -> {
            // Navigate to LoginActivity when the text is clicked
            Intent intent = new Intent(createAccount.this, loginActivity.class);
            startActivity(intent);
        });
    }

    private void handleRegister() {
        // Retrieving the input values
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create user with email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration successful, store additional user info in Realtime Database
                        FirebaseUser user = mAuth.getCurrentUser();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        // Create a user profile with additional information
                        UserProfile userProfile = new UserProfile(name, email);

                        // Save user profile in Realtime Database under a "users" node
                        DatabaseReference userRef = database.getReference("users").child(user.getUid());
                        userRef.setValue(userProfile)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(createAccount.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(createAccount.this, home.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(createAccount.this, "Error saving user info", Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        // If registration fails, display a message to the user.
                        Toast.makeText(createAccount.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Define a class to represent user profile
    public static class UserProfile {
        public String name;
        public String email;

        public UserProfile() {
            // Default constructor required for calls to DataSnapshot.getValue(UserProfile.class)
        }

        public UserProfile(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}
