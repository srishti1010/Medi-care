package com.example.hospital;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class createAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        // Get references to the views
        EditText firstNameInput = findViewById(R.id.first_name_edit_text);
        EditText middleNameInput = findViewById(R.id.middle_name_edit_text);
        EditText lastNameInput = findViewById(R.id.last_name_edit_text);
        EditText ageInput = findViewById(R.id.age_edit_text);
        EditText addressInput = findViewById(R.id.address_edit_text);
        EditText pincodeInput = findViewById(R.id.pincode_edit_text);
        EditText bloodGroupInput = findViewById(R.id.blood_group_edit_text);
        EditText emailInput = findViewById(R.id.email_edit_text);
        EditText phoneInput = findViewById(R.id.phone_edit_text);
        EditText passwordInput = findViewById(R.id.password_edit_text);
        EditText confirmPasswordInput = findViewById(R.id.confirm_password_edit_text);
        Button createAccountButton = findViewById(R.id.create_account_button);

        // Set up a click listener for the create account button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String firstName = firstNameInput.getText().toString().trim();
                String middleName = middleNameInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();
                String age = ageInput.getText().toString().trim();
                String address = addressInput.getText().toString().trim();
                String pincode = pincodeInput.getText().toString().trim();
                String bloodGroup = bloodGroupInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String phone = phoneInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String confirmPassword = confirmPasswordInput.getText().toString().trim();

                // Simple validation
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    // Show error (could use Toast, AlertDialog, etc.)
                    if (firstName.isEmpty()) firstNameInput.setError("First name is required");
                    if (lastName.isEmpty()) lastNameInput.setError("Last name is required");
                    if (email.isEmpty()) emailInput.setError("Email is required");
                    if (password.isEmpty()) passwordInput.setError("Password is required");
                    if (confirmPassword.isEmpty()) confirmPasswordInput.setError("Confirm password is required");
                } else if (!password.equals(confirmPassword)) {
                    // Check if passwords match
                    confirmPasswordInput.setError("Passwords do not match");
                } else {
                    // Save user details to SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("FirstName", firstName);
                    editor.putString("MiddleName", middleName);
                    editor.putString("LastName", lastName);
                    editor.putString("Age", age);
                    editor.putString("Address", address);
                    editor.putString("Pincode", pincode);
                    editor.putString("BloodGroup", bloodGroup);
                    editor.putString("Email", email);
                    editor.putString("Phone", phone);
                    editor.putString("Password", password); // Note: Storing passwords in plain text is not secure!
                    editor.apply();

                    // Notify user or redirect to another activity
                    // For example: Toast.makeText(CreateAccountActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
